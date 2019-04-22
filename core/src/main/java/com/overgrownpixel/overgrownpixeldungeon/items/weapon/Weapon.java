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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon;

import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Regrowth;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Bleeding;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FireImbue;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.MagicImmune;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Speed;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.Beam;
import com.overgrownpixel.overgrownpixeldungeon.effects.Lightning;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ChaosParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.KindOfWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Affection;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.AntiMagic;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Aqua;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Brimstone;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Chaotic;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Evasion;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Thorns;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfFuror;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfPoison;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Annoying;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Displacing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Elastic;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Exhausting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Fragile;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Friendly;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Polarized;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Sacrificial;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Wayward;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Absorbing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blazing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blooming;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Chilling;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Dazzling;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Disintegrating;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Eating;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Eldritch;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Explosion;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Flashing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Grim;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Hitting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Lucky;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Midas;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Momentum;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Precise;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Projecting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Shocking;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Stunning;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Swift;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Teleporting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.TimeReset;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Unstable;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Vampiric;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Venomous;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Vorpal;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Whirlwind;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Firefoxglove;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

abstract public class Weapon extends KindOfWeapon {

	private static final int HITS_TO_KNOW    = 20;

    public static final String AC_POSION		= "POISON";
    public static final String AC_DISINTEGRATE  = "DISINTEGRATE";
    public static final String AC_TELEPORT      = "TELEPORT";

    protected static final float TIME_TO_POISON	= 1f;

	public float    ACC = 1f;	// Accuracy modifier
	public float	DLY	= 1f;	// Speed modifier
	public int      RCH = 1;    // Reach modifier (only applies to melee hits)

	public enum Augment {
		SPEED   (0.7f, 0.6667f),
		DAMAGE  (1.5f, 1.6667f),
		NONE	(1.0f, 1.0000f);

		private float damageFactor;
		private float delayFactor;

		Augment(float dmg, float dly){
			damageFactor = dmg;
			delayFactor = dly;
		}

		public int damageFactor(int dmg){
			return Math.round(dmg * damageFactor);
		}

		public float delayFactor(float dly){
			return dly * delayFactor;
		}
	}

    {
        defaultAction = AC_POSION;
    }
	
	public Augment augment = Augment.NONE;

	private int hitsToKnow = HITS_TO_KNOW;
	
	public Enchantment enchantment;

	public Plant.Seed seed;

    public int torch_level = 0;
    public int poison_turns = 0;
    public boolean hasTeleport = false;
    public boolean hasDisintegrate = false;

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        if(seed == null && isIdentified() && !(this instanceof MissileWeapon) && !(this instanceof SpiritBow)){
            actions.add( AC_POSION );
        }
        if(this.hasEnchant(Disintegrating.class, hero) && this.isEquipped(hero) && isIdentified() && hasDisintegrate){
            actions.add( AC_DISINTEGRATE );
        }
        if(this.hasEnchant(Teleporting.class, hero) && this.isEquipped(hero) && isIdentified() && hasTeleport){
            actions.add( AC_TELEPORT );
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_POSION )) {
            GameScene.selectItem( itemSelector, mode, inventoryTitle );
        }

        if (action.equals( AC_DISINTEGRATE )) {
            new Disintegrating().disintegrate(hero, this);
        }

        if (action.equals( AC_TELEPORT )) {
            new Teleporting().teleport(hero, this);
        }
    }

    @Override
    public Emitter emitter() {
        if (this.seed == null) {
            return null;
        }
        Emitter emitter = new Emitter();
        emitter.pos(12.5f, 3.0f);
        emitter.fillTarget = false;
        emitter.pour(seed.getPixelParticle(), 1.0f);
        return emitter;
    }

    protected String inventoryTitle = Messages.get(this, "inv_title");

    protected WndBag.Mode mode = WndBag.Mode.SEED;

    protected static WndBag.Listener itemSelector = new WndBag.Listener() {
        @Override
        public void onSelect( Item item ) {

            if (item != null) {
                if(curItem instanceof Weapon && item instanceof Plant.Seed){
                    try {
                        ((Weapon) curItem).seed = (Plant.Seed) item.getClass().newInstance().quantity(1);
                        ((Weapon) curItem).setPoisonTurns(5, true);
                        item.detach(curUser.belongings.backpack);
                        curUser.spend( TIME_TO_POISON );
                        curUser.busy();
                        (curUser.sprite).operate(curUser.pos);
                        GLog.i(Messages.get(Weapon.class, "poisoned", curItem.name(), item.name()));
                    } catch (Exception e) {
                       OvergrownPixelDungeon.reportException(e);
                    }
                }
            }
        }
    };

    @Override
    public void doDrop(Hero hero) {
        torchLevel(hero);
        super.doDrop(hero);
    }

    @Override
    public void doThrow(Hero hero) {
        torchLevel(hero);
        super.doThrow(hero);
    }

    @Override
    public boolean doUnequip(Hero hero, boolean collect, boolean single) {
        torchLevel(hero);
        return super.doUnequip(hero, collect, single);
    }

    public void setPoisonTurns(int turns, boolean wasPoisonedByPlayer){
        if(wasPoisonedByPlayer){
            float rop = RingOfPoison.poisonMultiplier(curUser);
            this.poison_turns = (int) ((turns+this.level())*rop);
        } else {
            this.poison_turns = turns+this.level();
        }
    }

    private void torchLevel(Hero hero){
        if(this.torch_level != 0){
            this.torch_level = 0;
            if (Dungeon.level != null) {
                hero.sprite.remove(CharSprite.State.ILLUMINATED);
                hero.viewDistance = Dungeon.level.viewDistance;
            }
        }
    }

    @Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (enchantment != null && attacker.buff(MagicImmune.class) == null) {
			damage = enchantment.proc( this, attacker, defender, damage );
		}

		if(this.seed != null && this.poison_turns > 0){
		    this.seed.onProc(attacker, defender, damage);
		    this.poison_turns--;
		    if(this.poison_turns <= 0){
		        this.seed = null;
		        GLog.i(Messages.get(this, "wears_off", this.name()));
            }
        }
		
		if (!levelKnown && attacker == Dungeon.hero) {
			if (--hitsToKnow <= 0) {
				identify();
				GLog.i( Messages.get(Weapon.class, "identify") );
				Badges.validateItemLevelAquired( this );
			}
		}

		return damage;
	}

	private static final String UNFAMILIRIARITY	= "unfamiliarity";
	private static final String ENCHANTMENT		= "enchantment";
	private static final String AUGMENT			= "augment";
    private static final String SEED		    = "seed";
    private static final String TORCH_LEVEL		= "torchlevel";
    private static final String POISON_TURNS	= "poisonturns";
    private static final String HAS_TELEPORT	= "hasteleport";
    private static final String HAS_DISINTEGRATE= "hasdisintegrate";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( UNFAMILIRIARITY, hitsToKnow );
		bundle.put( ENCHANTMENT, enchantment );
		bundle.put( AUGMENT, augment );
        bundle.put( SEED, seed );
        bundle.put( TORCH_LEVEL, torch_level );
        bundle.put( POISON_TURNS, poison_turns );
        bundle.put( HAS_TELEPORT, hasTeleport );
        bundle.put( HAS_DISINTEGRATE, hasDisintegrate );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		hitsToKnow = bundle.getInt( UNFAMILIRIARITY );
		enchantment = (Enchantment)bundle.get( ENCHANTMENT );
        seed = (Plant.Seed) bundle.get( SEED );
        torch_level = bundle.getInt(TORCH_LEVEL);
        poison_turns = bundle.getInt(POISON_TURNS);
        hasTeleport = bundle.getBoolean( HAS_TELEPORT );
        hasDisintegrate = bundle.getBoolean( HAS_DISINTEGRATE );
	}
	
	@Override
	public float accuracyFactor( Char owner ) {
		
		int encumbrance = 0;
		
		if( owner instanceof Hero ){
			encumbrance = STRReq() - ((Hero)owner).STR();
		}

		if (hasEnchant(Wayward.class, owner))
			encumbrance = Math.max(2, encumbrance+2);

		float ACC = this.ACC;

		return encumbrance > 0 ? (float)(ACC / Math.pow( 1.5, encumbrance )) : ACC;
	}
	
	@Override
	public float speedFactor( Char owner ) {

		int encumbrance = 0;
		if (owner instanceof Hero) {
			encumbrance = STRReq() - ((Hero)owner).STR();
		}

		float DLY = augment.delayFactor(this.DLY);

		DLY *= RingOfFuror.attackDelayMultiplier(owner);

		return (encumbrance > 0 ? (float)(DLY * Math.pow( 1.2, encumbrance )) : DLY);
	}

	@Override
	public int reachFactor(Char owner) {
		return hasEnchant(Projecting.class, owner) ? RCH+1 : RCH;
	}

	public int STRReq(){
		return STRReq(level());
	}

	public abstract int STRReq(int lvl);
	
	@Override
	public Item upgrade() {
		return upgrade(false);
	}
	
	public Item upgrade(boolean enchant ) {

		if (enchant && (enchantment == null || enchantment.curse())){
			enchant( Enchantment.random() );
		} else if (!enchant && Random.Float() > Math.pow(0.9, level())){
			enchant(null);
		}
		
		cursed = false;
		
		return super.upgrade();
	}
	
	@Override
	public String name() {
		return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.name( super.name() ) : super.name();
	}

    @Override
    public String desc() {
	    if(this.seed != null){
	        //TODO: Custom text for each seed
            return super.desc()+"\n\n"+Messages.get(this, "poison_desc", this.seed, this.poison_turns);
        } else {
	        return super.desc();
        }
    }

    @Override
	public Item random() {
		//+0: 75% (3/4)
		//+1: 20% (4/20)
		//+2: 5%  (1/20)
		int n = 0;
		if (Random.Int(4) == 0) {
			n++;
			if (Random.Int(5) == 0) {
				n++;
			}
		}
		level(n);
		
		//30% chance to be cursed
		//10% chance to be enchanted
		float effectRoll = Random.Float();
		if (effectRoll < 0.3f) {
			enchant(Enchantment.randomCurse());
			cursed = true;
		} else if (effectRoll >= 0.9f){
			enchant();
		}

		return this;
	}
	
	public Weapon enchant( Enchantment ench ) {
		enchantment = ench;
		updateQuickslot();
		return this;
	}

	public Weapon enchant() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.random( oldEnchantment );

		return enchant( ench );
	}

	public boolean hasEnchant(Class<?extends Enchantment> type, Char owner) {
		return enchantment != null && enchantment.getClass() == type && owner.buff(MagicImmune.class) == null;
	}
	
	//these are not used to process specific enchant effects, so magic immune doesn't affect them
	public boolean hasGoodEnchant(){
		return enchantment != null && !enchantment.curse();
	}

	public boolean hasCurseEnchant(){
		return enchantment != null && enchantment.curse();
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.glowing() : null;
	}

	public static abstract class Enchantment implements Bundlable {
		
		private static final Class<?>[] common = new Class<?>[]{
				Blazing.class, Venomous.class, Vorpal.class, Shocking.class, Blooming.class, Absorbing.class, Momentum.class, Hitting.class};
		
		private static final Class<?>[] uncommon = new Class<?>[]{
				Chilling.class, Eldritch.class, Lucky.class, Precise.class,
				Projecting.class, Unstable.class, Dazzling.class, Flashing.class, Midas.class, TimeReset.class, Eating.class};
		
		private static final Class<?>[] rare = new Class<?>[]{
				Grim.class, Stunning.class, Vampiric.class, Explosion.class, Whirlwind.class, Swift.class, Teleporting.class,
                Disintegrating.class};
		
		private static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};
		
		private static final Class<?>[] curses = new Class<?>[]{
				Annoying.class, Displacing.class, Exhausting.class, Fragile.class,
				Sacrificial.class, Wayward.class, Elastic.class, Friendly.class, Polarized.class
		};
		
			
		public abstract int proc( Weapon weapon, Char attacker, Char defender, int damage );

		public String name() {
			if (!curse())
				return name( Messages.get(this, "enchant"));
			else
				return name( Messages.get(Item.class, "curse"));
		}

		public String name( String weaponName ) {
			return Messages.get(this, "name", weaponName);
		}

		public String desc() {
			return Messages.get(this, "desc");
		}

		public boolean curse() {
			return false;
		}

		public static void comboProc(Enchantment enchantment, Armor.Glyph glyph, Char attacker, Char defender, int damage){

		    //giant explosion
		    if(enchantment instanceof Explosion && glyph instanceof com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Explosion){
		        for(int ec : PathFinder.NEIGHBOURS8){
		            new Explosion().explode(ec, attacker);
                }
            }

            //plant explosion
            if(enchantment instanceof Blooming && glyph instanceof com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Explosion){
                GameScene.add( Blob.seed( attacker.pos, 100, Regrowth.class ) );
            }

            //shoot flames at everything
            if(enchantment instanceof Blazing && glyph instanceof Brimstone){
                for(Mob mob : Dungeon.level.mobs){
                    if(Dungeon.level.heroFOV[mob.pos]){
                        new Firefoxglove().shoot(attacker, mob.pos);
                    }
                }
            }

            //love... is a ring of fire...
            if(enchantment instanceof Blazing && glyph instanceof Affection){
                Buff.affect(attacker, FireImbue.class).set(FireImbue.DURATION);
                for(int fp : PathFinder.CIRCLE8){
                    GameScene.add(Blob.seed(defender.pos+fp, 1, Fire.class));
                }
            }

            //thorns fly
            if(enchantment instanceof Whirlwind && glyph instanceof Thorns){
                for(Mob mob : Dungeon.level.mobs){
                    if(attacker instanceof Hero){
                        if(((Hero) attacker).belongings.weapon instanceof MeleeWeapon){
                            Weapon weapon = (Weapon) ((Hero) attacker).belongings.weapon;
                            if(Dungeon.level.distance(attacker.pos, mob.pos) <= weapon.RCH){
                                Buff.affect( mob, Bleeding.class).set( Math.max( 2, damage));
                            }
                        }
                    }
                }
            }

            //summon forces from beyond the veil of sleep
            if(enchantment instanceof Unstable && glyph instanceof Chaotic){
                attacker.sprite.emitter().start(ChaosParticle.FACTORY, 0.9f, 5);
                for(Mob mob : Dungeon.level.mobs){
                    if(Dungeon.level.heroFOV[mob.pos]){
                        attacker.sprite.parent.add(new Beam.DeathRay(attacker.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( defender.pos )));
                        Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
                        try{
                            seed.getPlantClass().newInstance().activate(defender);
                        } catch (Exception e){
                            OvergrownPixelDungeon.reportException(e);
                        }
                    }
                }
            }

            //demonic entites are really not gonna like this
            if(enchantment instanceof Eldritch && glyph instanceof AntiMagic) {
                if (defender.properties().contains(Char.Property.DEMONIC)) {
                    defender.damage(Math.round(damage / 3), attacker);
                }
            }

            //water and electricity make for some good shit
            if(enchantment instanceof Shocking && glyph instanceof Aqua) {
                ArrayList<Lightning.Arc> arcs = new ArrayList<>();
                for(Mob mob : Dungeon.level.mobs){
                    if(Dungeon.level.water[mob.pos] && Dungeon.level.heroFOV[mob.pos]){
                        arcs.add(new Lightning.Arc(attacker.sprite.center(), mob.sprite.center()));
                        mob.damage(Math.round(damage/3), attacker);
                    }
                }
                if(!arcs.isEmpty()){
                    attacker.sprite.parent.addToFront( new Lightning( arcs, null ) );
                }
            }

            //super reflexes
            if(enchantment instanceof Swift && glyph instanceof Evasion) {
                Buff.prolong( attacker, Speed.class, Speed.DURATION );
            }

            //Killa Queen
            if(enchantment instanceof TimeReset && glyph instanceof com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs.Explosion) {
                for(Mob mob : Dungeon.level.mobs){
                    if(Dungeon.level.heroFOV[mob.pos]){
                        new Explosion().explode(mob.pos, attacker);
                    }
                }
            }

        }

		@Override
		public void restoreFromBundle( Bundle bundle ) {
		}

		@Override
		public void storeInBundle( Bundle bundle ) {
		}
		
		public abstract ItemSprite.Glowing glowing();
		
		@SuppressWarnings("unchecked")
		public static Enchantment random( Class<? extends Enchantment> ... toIgnore ) {
			switch(Random.chances(typeChances)){
				case 0: default:
					return randomCommon( toIgnore );
				case 1:
					return randomUncommon( toIgnore );
				case 2:
					return randomRare( toIgnore );
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomCommon( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(common));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomUncommon( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(uncommon));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomRare( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(rare));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		public static Enchantment randomCurse( Class<? extends Enchantment> ... toIgnore ){
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(curses));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}

        public void affectTarget(Ballistica shot, Hero curUser) {}
    }
}
