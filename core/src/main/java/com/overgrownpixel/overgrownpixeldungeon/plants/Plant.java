/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Haste;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Lasher;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.livingplants.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfElements;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfPoison;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.features.Door;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Plant implements Bundlable {

	public String plantName = Messages.get(this, "name");
	
	public int image;
	public int pos;

	public void trigger(){

		Char ch = Actor.findChar(pos);

		if (ch instanceof Hero){
			((Hero) ch).interrupt();
		}

		wither();

		if(!(this instanceof Rotberry)){
            if(ch instanceof LivingPlant){
                //livingplants will trigger other living plants. Since livpla's will bot step on other plants on purpose this only happens if they are pushed onto a plant.
                ch.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );
                spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
            } else {
                //30% chance of triggering a living plant
                float chance = 0.3f;
                for(int i : PathFinder.NEIGHBOURS8){
                    if(Dungeon.level.map[pos+i] == Terrain.HIGH_GRASS ||
                            Dungeon.level.map[pos+i] == Terrain.WALL ||
                            Dungeon.level.map[pos+i] == Terrain.WALL_DECO){
                        //8x0.0375 = 0.3f
                        chance -= 0.0375f;
                    }
                    if(Dungeon.level.map[pos+i] == Terrain.WELL ||
                            Dungeon.level.map[pos+i] == Terrain.WATER ||
                            Dungeon.level.map[pos+i] == Terrain.ALCHEMY){
                        chance += 0.0375f;
                    }
                }
                //chances (int) in 100 or in other words 30%
                if(Random.Float() <= chance){
                    //spawn the living plant
                    spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
                } else {
                    if(ch != null){
                        if(ch.properties().contains(Char.Property.INORGANIC)){
                            spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
                        }
                        //if the character exits trigger the plant effect on the character
                        activate( ch );
                    } else {
                        //if the character doesn't exist 50% chance to activate a variation effect of the plant that doesn't need a ch
                        if(Random.Boolean()){
                            activate();
                        } else {
                            spawnLivingPlant(new LivingPlant().setPlantClass(this), null);
                        }
                    }
                }
            }
        } else {
		    activate(ch);
        }
	}

	public static void spawnLasher(int pos){
	    //try and spawn a lasher. This is only reserved for when you have no idea what an activate() effect should be.
        if (Actor.findChar(pos) == null && ((Dungeon.level.passable[pos] || Dungeon.level.avoid[pos]) && !Dungeon.level.pit[pos])) {
            Lasher lasher = new Lasher();
            lasher.pos = pos;
            GameScene.add(lasher);
            Actor.addDelayed(new Pushing(lasher, pos, lasher.pos), 0.0f);
            lasher.move(lasher.pos);
        } else {
            //fuck it! Give the player a seed! They deserve it if they managed to pull this fucking shit off!
            Dungeon.level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop(pos);
        }
    }

    public void spawnLivingPlant(LivingPlant livingPlant, Char activator) {
	    //find the all tiles the living plant can be spawned at
        Collection arrayList = new ArrayList();
        for (int i : PathFinder.NEIGHBOURS8) {
            int ip = pos + i;
            //this if statement makes it impossible for livingplants to be spawned on (avoid) other plant terrain.
            if (Actor.findChar(ip) == null && ((Dungeon.level.passable[ip] || Dungeon.level.avoid[ip]) && !Dungeon.level.pit[ip])) {
                arrayList.add(Integer.valueOf(ip));
            }
        }

        //checks if there are any tiles
        if (!arrayList.isEmpty()) {
            livingPlant.state = livingPlant.HUNTING;
            livingPlant.pos = ((Integer) Random.element(arrayList)).intValue();
            GameScene.add(livingPlant);
            Actor.addDelayed(new Pushing(livingPlant, pos, livingPlant.pos), 0.0f);
            livingPlant.move(livingPlant.pos);
            if (Dungeon.level.map[livingPlant.pos] == 5) {
                Door.enter(livingPlant.pos);
            }
            if (Dungeon.level.heroFOV[pos]) {
                CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
            }
            if (Dungeon.level.heroFOV[livingPlant.pos]) {
                CellEmitter.get( livingPlant.pos ).burst( LeafParticle.GENERAL, 6 );
            }
            if (Dungeon.level.map[livingPlant.pos] == Terrain.EMBERS
                    || Dungeon.level.map[livingPlant.pos] == Terrain.EMPTY_DECO
                    || Dungeon.level.map[livingPlant.pos] == Terrain.EMPTY
                    || Dungeon.level.map[livingPlant.pos] == Terrain.HIGH_GRASS){
                Dungeon.level.map[livingPlant.pos] = Terrain.GRASS;
            }
            if (Dungeon.level.plants.get(livingPlant.pos) != null){
                Dungeon.level.plants.get(livingPlant.pos).activate(livingPlant);
            }
            GameScene.updateMap(pos);
        } else {
            //if there aren't any tiles...
            if(activator instanceof LivingPlant){
                //if the activator was a fellow plant, it gets a speed boost
                Buff.prolong( activator, Haste.class, Haste.DURATION);
            } else {
                if(activator != null){
                    //if the activator is anything else just activate the effect on it
                    activate( activator );
                } else {
                    //this can only happen if there are no tiles and the plant was triggered by an item
                    activate();
                }
            }
        }
    }

    public abstract void attackProc(Char enemy, int damage);
	
	public abstract void activate( Char ch );

    public abstract void activate();

    public void activatePosionDangerous( Char attacker, Char defender ){

    }

    public void activatePosionMobBeneficial(Char attacker, Char defender ){

    }
	
	public void wither() {
		Dungeon.level.uproot( pos );

		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
		}

		int ringBuff = 0;
		if(Dungeon.hero.belongings.misc1 instanceof RingOfPoison){
		    ringBuff += 2;
        }
        if(Dungeon.hero.belongings.misc2 instanceof RingOfPoison){
            ringBuff += 2;
        }
        if(Dungeon.hero.belongings.misc1 instanceof RingOfElements){
            ringBuff += 2;
        }
        if(Dungeon.hero.belongings.misc2 instanceof RingOfElements){
            ringBuff += 2;
        }
        if (Random.Int(14-ringBuff) <= 0){
            Dungeon.level.drop(getPlant(this), pos).sprite.drop();
        }
		
	}

	//TODO: Update with new plants
	public static Plant.Seed getPlant(Plant plant){
	    switch (plant.image){
            case 0:
                return new Rotberry.Seed();
            case 1:
                return new Firebloom.Seed();
            case 2:
                return new Swiftthistle.Seed();
            case 3:
                return new Sungrass.Seed();
            case 4:
                return new Icecap.Seed();
            case 5:
                return new Stormvine.Seed();
            case 6:
                return new Sorrowmoss.Seed();
            case 7:
                return new Dreamfoil.Seed();
            case 8:
                return new Earthroot.Seed();
            case 9:
                return new Starflower.Seed();
            case 10:
                return new Fadeleaf.Seed();
            case 11:
                return new Blindweed.Seed();
            case 12:
                return new BlandfruitBush.Seed();
            case 13:
                return new WandOfRegrowth.Dewcatcher.Seed();
            case 14:
                return new WandOfRegrowth.Seedpod.Seed();
            case 15:
                return new Apricobush.Seed();
            case 16:
                return new Firefoxglove.Seed();
            case 17:
                return new Butterlion.Seed();
            case 18:
                return new Musclemoss.Seed();
            case 19:
                return new Snowhedge.Seed();
            case 20:
                return new Sunbloom.Seed();
            case 21:
                return new Steamweed.Seed();
            case 22:
                return new Tomatobush.Seed();
            case 23:
                return new Chandaliertail.Seed();
            case 24:
                return new Nightshadeonion.Seed();
            case 25:
                return new Blackholeflower.Seed();
            case 26:
                return new Frostcorn.Seed();
            case 27:
                return new Willowcane.Seed();
            case 28:
                return new Parasiteshrub.Seed();
            case 29:
                return new Crimsonpepper.Seed();
            case 30:
                return new Witherfennel.Seed();
            case 31:
                return new Chillisnapper.Seed();
            case 32:
                return new Waterweed.Seed();
            case 33:
                return new Grasslilly.Seed();
            case 34:
                return new Peanutpetal.Seed();
            case 35:
                return new Kiwivetch.Seed();
            case 36:
                return new Rose.Seed();
            case 37:
                return new Venusflytrap.Seed();
            case 38:
                return new Suncarnivore.Seed();
            default:
                return null;
        }
    }
	
	private static final String POS	= "pos";

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		pos = bundle.getInt( POS );
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( POS, pos );
	}
	
	public String desc() {
		return Messages.get(this, "desc");
	}
	
	public static class Seed extends Item {

        public enum HeroDanger{
            DANGEROUS(1),
            NEUTRAL(2),
            BENEFICIAL(3);

            HeroDanger(int i) {

            }
        }

        public HeroDanger heroDanger;

		public static final String AC_PLANT	= "PLANT";
		
		private static final float TIME_TO_PLANT = 1f;
		
		{
			stackable = true;
			defaultAction = AC_THROW;
		}

        protected Class<? extends Plant> plantClass;

        public Class<? extends Plant> getPlantClass() {
            return this.plantClass;
        }

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
        }

        @Override
		public ArrayList<String> actions( Hero hero ) {
			ArrayList<String> actions = super.actions( hero );
			actions.add( AC_PLANT );
			return actions;
		}

		public void onProc( Char attacker, Char defender, int damage){
            //attacker is the weapon wielder
            //damage is used to scale effects
            //defender is the poor victim

            //also poison can not effect inorganic, acidic and fiery actors for obvious reasons
            if(!defender.properties().contains(Char.Property.INORGANIC)
                    && !defender.properties().contains(Char.Property.ACIDIC)
                    && !defender.properties().contains(Char.Property.FIERY)){
                try {
                    Plant plant = plantClass.newInstance();
                    plant.pos = defender.pos;
                    switch (heroDanger){
                            //this activates if the activation of the plant on the defender pos would also have negative effects on the attacker
                        case DANGEROUS:
                            plant.activatePosionDangerous(attacker, defender);
                            break;
                            //this happens if the activation of the plant at the defender pos wouldn't harm the attacker
                        case NEUTRAL:
                            plant.activate(defender);
                            break;
                        case BENEFICIAL:
                            //this happens if the effect of plant has a positive effect and will activate alternative harming effect for its poison variant
                            plant.activatePosionMobBeneficial(attacker, defender);
                            break;
                    }
                } catch (Exception e) {
                    OvergrownPixelDungeon.reportException(e);
                }
            }

        }

        public Emitter.Factory getPixelParticle(){
		    return null;
        }
		
		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.map[cell] == Terrain.ALCHEMY
					|| Dungeon.level.pit[cell]
					|| Dungeon.level.traps.get(cell) != null
					|| Dungeon.isChallenged(Challenges.NO_HERBALISM)) {
				super.onThrow( cell );
			} else {
				Dungeon.level.plant( this, cell, false);
				if (Dungeon.hero.subClass == HeroSubClass.WARDEN) {
					for (int i : PathFinder.NEIGHBOURS8) {
						int c = Dungeon.level.map[cell + i];
						if ( c == Terrain.EMPTY || c == Terrain.EMPTY_DECO
								|| c == Terrain.EMBERS || c == Terrain.GRASS){
							Level.set(cell + i, Terrain.FURROWED_GRASS);
							GameScene.updateMap(cell + i);
							CellEmitter.get( cell + i ).burst( LeafParticle.LEVEL_SPECIFIC, 4 );
						}
					}
				}
			}
		}
		
		@Override
		public void execute( Hero hero, String action ) {

			super.execute (hero, action );

			if (action.equals( AC_PLANT )) {
							
				hero.spend( TIME_TO_PLANT );
				hero.busy();
				((Seed)detach( hero.belongings.backpack )).onThrow( hero.pos );
				
				hero.sprite.operate( hero.pos );
				
			}
		}
		
		public Plant couch( int pos, Level level ) {
			try {
				if (level != null && level.heroFOV != null && level.heroFOV[pos]) {
					Sample.INSTANCE.play(Assets.SND_PLANT);
				}
				Plant plant = plantClass.newInstance();
				plant.pos = pos;
				return plant;
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}

		public Plant getPlant(int pos){
            try {
                Plant plant = plantClass.newInstance();
                plant.pos = pos;
                return plant;
            } catch (Exception e) {
                OvergrownPixelDungeon.reportException(e);
                return null;
            }
        }
		
		@Override
		public boolean isUpgradable() {
			return false;
		}
		
		@Override
		public boolean isIdentified() {
			return true;
		}
		
		@Override
		public int price() {
			return 10 * quantity;
		}

		@Override
		public String desc() {
			return Messages.get(plantClass, "desc");
		}

		@Override
		public String info() {
			return Messages.get( Seed.class, "info", desc() );
		}

        public static class PlaceHolder extends Seed {
			
			{
				image = ItemSpriteSheet.SEED_HOLDER;
			}
			
			@Override
			public boolean isSimilar(Item item) {
				return item instanceof Plant.Seed;
			}
			
			@Override
			public String info() {
				return "";
			}
		}
	}
}
