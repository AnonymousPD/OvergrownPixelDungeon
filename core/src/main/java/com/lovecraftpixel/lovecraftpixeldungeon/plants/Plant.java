/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
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

package com.lovecraftpixel.lovecraftpixeldungeon.plants;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Challenges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Haste;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.HeroSubClass;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.livingplants.LivingPlant;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Pushing;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.LeafParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.features.Door;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
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
		if(ch instanceof LivingPlant){
            ((LivingPlant) ch).powerlevel++;
            ch.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );
            spawnLivingPlant(new LivingPlant().setPlantClass(this, 1), ch);
        } else {
            if(Random.Int(10) >= 7){
                spawnLivingPlant(new LivingPlant().setPlantClass(this, 1), ch);
            } else {
                activate( ch );
            }
        }
	}

    public void spawnLivingPlant(LivingPlant livingPlant, Char activator) {
        Collection arrayList = new ArrayList();
        for (int i : PathFinder.NEIGHBOURS8) {
            int ip = pos + i;
            if (Actor.findChar(ip) == null && ((Dungeon.level.passable[ip] || Dungeon.level.avoid[ip]) && !Dungeon.level.pit[ip])) {
                arrayList.add(Integer.valueOf(ip));
            }
        }
        if (!arrayList.isEmpty()) {
            livingPlant.state = livingPlant.HUNTING;
            livingPlant.pos = ((Integer) Random.element(arrayList)).intValue();
            GameScene.add(livingPlant);
            Actor.addDelayed(new Pushing(livingPlant, pos, livingPlant.pos), 0.0f);
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
            if(activator instanceof LivingPlant){
                Buff.prolong( activator, Haste.class, Haste.DURATION);
            } else {
                activate( activator );
            }
        }
    }
	
	public abstract void activate( Char ch );
	
	public void wither() {
		Dungeon.level.uproot( pos );

		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
		}
		
	}

	public Plant.Seed getPlant(Plant plant){
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

		public static final String AC_PLANT	= "PLANT";
		
		private static final float TIME_TO_PLANT = 1f;
		
		{
			stackable = true;
			defaultAction = AC_THROW;
		}
		
		protected Class<? extends Plant> plantClass;
		
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
            try {
                Plant plant = plantClass.newInstance();
                plant.pos = defender.pos;
                plant.activate(defender);
            } catch (Exception e) {
                LovecraftPixelDungeon.reportException(e);
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
				Dungeon.level.plant( this, cell );
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
				LovecraftPixelDungeon.reportException(e);
				return null;
			}
		}

		public Plant getPlant(int pos){
            try {
                Plant plant = plantClass.newInstance();
                plant.pos = pos;
                return plant;
            } catch (Exception e) {
                LovecraftPixelDungeon.reportException(e);
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
