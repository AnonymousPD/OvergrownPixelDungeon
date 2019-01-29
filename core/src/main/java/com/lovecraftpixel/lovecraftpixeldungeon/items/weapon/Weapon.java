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

package com.lovecraftpixel.lovecraftpixeldungeon.items.weapon;

import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Healing;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.MagicImmune;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.KindOfWeapon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfFuror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfPoison;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Annoying;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Displacing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Elastic;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Exhausting;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Fragile;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Friendly;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Sacrificial;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.curses.Wayward;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Blazing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Chilling;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Dazzling;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Eldritch;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Explosion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Flashing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Grim;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Lucky;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Projecting;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Shocking;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Stunning;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Unstable;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Vampiric;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Venomous;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Vorpal;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Whirlwind;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.HeroSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndBag;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

abstract public class Weapon extends KindOfWeapon {

	private static final int HITS_TO_KNOW    = 20;

    public static final String AC_POSION		= "POISON";

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
	
	public Augment augment = Augment.NONE;

	private int hitsToKnow = HITS_TO_KNOW;
	
	public Enchantment enchantment;

	public Plant.Seed seed;

    public int torch_level = 0;
    public int poison_turns = 0;

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        if(seed == null){
            actions.add( AC_POSION );
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_POSION )) {
            GameScene.selectItem( itemSelector, mode, inventoryTitle );
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
                    ((Weapon) curItem).seed = (Plant.Seed) item;
                    ((Weapon) curItem).setPoisonTurns(5, true);
                    curUser.belongings.backpack.items.remove(item);
                    curUser.spend( TIME_TO_POISON );
                    curUser.busy();
                    (curUser.sprite).operate(curUser.pos);
                    GLog.i(Messages.get(Weapon.class, "poisoned", curItem.name(), item.name()));
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

    private void setPoisonTurns(int turns, boolean wasPoisonedByPlayer){
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

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( UNFAMILIRIARITY, hitsToKnow );
		bundle.put( ENCHANTMENT, enchantment );
		bundle.put( AUGMENT, augment );
        bundle.put( SEED, seed );
        bundle.put( TORCH_LEVEL, torch_level );
        bundle.put( POISON_TURNS, poison_turns );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		hitsToKnow = bundle.getInt( UNFAMILIRIARITY );
		enchantment = (Enchantment)bundle.get( ENCHANTMENT );
        seed = (Plant.Seed) bundle.get( SEED );
        torch_level = bundle.getInt(TORCH_LEVEL);
        poison_turns = bundle.getInt(POISON_TURNS);
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
            return super.desc()+"\n\n"+Messages.get(this, "poison_desc", this.seed, this.poison_turns)+"\n"+Messages.get(this.seed, "weapon_desc");
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
				Blazing.class, Venomous.class, Vorpal.class, Shocking.class};
		
		private static final Class<?>[] uncommon = new Class<?>[]{
				Chilling.class, Eldritch.class, Lucky.class,
				Projecting.class, Unstable.class, Dazzling.class, Flashing.class};
		
		private static final Class<?>[] rare = new Class<?>[]{
				Grim.class, Stunning.class, Vampiric.class, Explosion.class, Whirlwind.class};
		
		private static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};
		
		private static final Class<?>[] curses = new Class<?>[]{
				Annoying.class, Displacing.class, Exhausting.class, Fragile.class,
				Sacrificial.class, Wayward.class, Elastic.class, Friendly.class
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
				LovecraftPixelDungeon.reportException(e);
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
				LovecraftPixelDungeon.reportException(e);
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
				LovecraftPixelDungeon.reportException(e);
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
				LovecraftPixelDungeon.reportException(e);
				return null;
			}
		}
		
	}
}
