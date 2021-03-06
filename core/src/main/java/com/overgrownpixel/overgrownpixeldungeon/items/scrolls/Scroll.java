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

package com.overgrownpixel.overgrownpixeldungeon.items.scrolls;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Glowing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.MagicImmune;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Wraith;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.ItemStatusHandler;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.UnstableSpellbook;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.special.RoyalDecreeOfTheEmperor;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.Runestone;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfAffection;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfAggression;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfAugmentation;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfBlast;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfBlink;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfClairvoyance;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfDeepenedSleep;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfDetectCurse;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfEnchantment;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfFlock;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfIntuition;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfShock;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.journal.Catalog;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.HeroSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Scroll extends Item {
	
	public static final String AC_READ	= "READ";
	
	protected static final float TIME_TO_READ	= 1f;

	protected Integer initials;

	private static final Class<?>[] scrolls = {
		    ScrollOfIdentify.class,
		    ScrollOfMagicMapping.class,
		    ScrollOfRecharging.class,
		    ScrollOfRemoveCurse.class,
		    ScrollOfTeleportation.class,
		    ScrollOfUpgrade.class,
		    ScrollOfRage.class,
		    ScrollOfTerror.class,
		    ScrollOfLullaby.class,
		    ScrollOfTransmutation.class,
		    ScrollOfRetribution.class,
		    ScrollOfMirrorImage.class,
            RoyalDecreeOfTheEmperor.class
	};

	private static final HashMap<String, Integer> runes = new HashMap<String, Integer>() {
		{
			put("KAUNAN",ItemSpriteSheet.SCROLL_KAUNAN);
			put("SOWILO",ItemSpriteSheet.SCROLL_SOWILO);
			put("LAGUZ",ItemSpriteSheet.SCROLL_LAGUZ);
			put("YNGVI",ItemSpriteSheet.SCROLL_YNGVI);
			put("GYFU",ItemSpriteSheet.SCROLL_GYFU);
			put("RAIDO",ItemSpriteSheet.SCROLL_RAIDO);
			put("ISAZ",ItemSpriteSheet.SCROLL_ISAZ);
			put("MANNAZ",ItemSpriteSheet.SCROLL_MANNAZ);
			put("NAUDIZ",ItemSpriteSheet.SCROLL_NAUDIZ);
			put("BERKANAN",ItemSpriteSheet.SCROLL_BERKANAN);
			put("ODAL",ItemSpriteSheet.SCROLL_ODAL);
			put("TIWAZ",ItemSpriteSheet.SCROLL_TIWAZ);
            put("JERA",ItemSpriteSheet.SCROLL_JERA);
		}
	};
	
	protected static ItemStatusHandler<Scroll> handler;
	
	protected String rune;
	
	{
		stackable = true;
		defaultAction = AC_READ;
	}
	
	@SuppressWarnings("unchecked")
	public static void initLabels() {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])scrolls, runes );
	}
	
	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		ArrayList<Class<?extends Item>> classes = new ArrayList<>();
		for (Item i : items){
			if (i instanceof ExoticScroll){
				if (!classes.contains(ExoticScroll.exoToReg.get(i.getClass()))){
					classes.add(ExoticScroll.exoToReg.get(i.getClass()));
				}
			} else if (i instanceof Scroll){
				if (!classes.contains(i.getClass())){
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively( bundle, classes );
	}

	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])scrolls, runes, bundle );
	}
	
	public Scroll() {
		super();
		reset();
	}
	
	//anonymous scrolls are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = ItemSpriteSheet.SCROLL_HOLDER;
		anonymous = true;
	}
	
	
	@Override
	public void reset(){
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			rune = handler.label(this);
		}
	};
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_READ );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_READ )) {
			
			if (hero.buff(MagicImmune.class) != null){
				GLog.w( Messages.get(this, "no_magic") );
			} else if (hero.buff( Blindness.class ) != null) {
				GLog.w( Messages.get(this, "blinded") );
			} else if (hero.buff(UnstableSpellbook.bookRecharge.class) != null
					&& hero.buff(UnstableSpellbook.bookRecharge.class).isCursed()
					&& !(this instanceof ScrollOfRemoveCurse || this instanceof ScrollOfAntiMagic)){
				GLog.n( Messages.get(this, "cursed") );
			} else {
			    if(hero.intelligence <= 0 && !(this instanceof ScrollOfUpgrade)){
                    curUser = hero;
                    curItem = detach( hero.belongings.backpack );
                    GLog.n( Messages.get(this, "miscast") );
                    doMiscast();
                    readAnimation();
                } else if(hero.intelligence <= Random.Int(10, 20) && !(this instanceof ScrollOfUpgrade) && Random.Boolean()){
                    curUser = hero;
                    curItem = detach( hero.belongings.backpack );
                    GLog.n( Messages.get(this, "miscast") );
                    doMiscast();
                    readAnimation();
                } else {
			        hero.intelligence++;
                    curUser = hero;
                    curItem = detach( hero.belongings.backpack );
                    doRead();
                }
			}
			
		}
	}
	
	public abstract void doRead();

	//here are all the things that can happen if you miscast anything: spell or scroll
    public static void doMiscast(){
        switch (Random.Int(0, 3)){
            case 0:
                int opposite;
                do{
                    opposite = curUser.pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                } while (Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO);
                Ballistica trajectory = new Ballistica(curUser.pos, opposite, Ballistica.MAGIC_BOLT);
                WandOfBlastWave.throwChar(curUser, trajectory, 100);
                break;
            case 1:
                Buff.affect(curUser, Glowing.class).reignite(curUser);
                break;
            case 2:
                GameScene.flash( Random.Int(0xFDA06E, 0xADFD6E) );
                Buff.prolong(curUser, Blindness.class, Math.round(10));
                break;
            case 3:
                Wraith.spawnAround(curUser.pos);
                break;
        }
    }
	
	//currently only used in scrolls owned by the unstable spellbook
	public abstract void empoweredRead();

	protected void readAnimation() {
		curUser.spend( TIME_TO_READ );
		curUser.busy();
		((HeroSprite)curUser.sprite).read();
	}
	
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}
	
	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
			}
			
			if (Dungeon.hero.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}
	
	@Override
	public Item identify() {
		setKnown();
		return super.identify();
	}
	
	@Override
	public String name() {
		return isKnown() ? name : Messages.get(this, rune);
	}
	
	@Override
	public String info() {
		return isKnown() ?
			desc() :
			Messages.get(this, "unknown_desc");
	}

	public Integer initials(){
		return isKnown() ? initials : null;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return isKnown();
	}
	
	public static HashSet<Class<? extends Scroll>> getKnown() {
		return handler.known();
	}
	
	public static HashSet<Class<? extends Scroll>> getUnknown() {
		return handler.unknown();
	}
	
	public static boolean allKnown() {
		return handler.known().size() == scrolls.length;
	}
	
	@Override
	public int price() {
		return 30 * quantity;
	}
	
	public static class ScrollToStone extends Recipe {
		
		private static HashMap<Class<?extends Scroll>, Class<?extends Runestone>> stones = new HashMap<>();
		private static HashMap<Class<?extends Scroll>, Integer> amnts = new HashMap<>();
		static {
			stones.put(ScrollOfIdentify.class,      StoneOfIntuition.class);
			amnts.put(ScrollOfIdentify.class,       3);
			
			stones.put(ScrollOfLullaby.class,       StoneOfDeepenedSleep.class);
			amnts.put(ScrollOfLullaby.class,        3);
			
			stones.put(ScrollOfMagicMapping.class,  StoneOfClairvoyance.class);
			amnts.put(ScrollOfMagicMapping.class,   3);
			
			stones.put(ScrollOfMirrorImage.class,   StoneOfFlock.class);
			amnts.put(ScrollOfMirrorImage.class,    3);
			
			stones.put(ScrollOfRetribution.class,   StoneOfBlast.class);
			amnts.put(ScrollOfRetribution.class,    2);
			
			stones.put(ScrollOfRage.class,          StoneOfAggression.class);
			amnts.put(ScrollOfRage.class,           3);
			
			stones.put(ScrollOfRecharging.class,    StoneOfShock.class);
			amnts.put(ScrollOfRecharging.class,     2);
			
			stones.put(ScrollOfRemoveCurse.class,   StoneOfDetectCurse.class);
			amnts.put(ScrollOfRemoveCurse.class,    2);
			
			stones.put(ScrollOfTeleportation.class, StoneOfBlink.class);
			amnts.put(ScrollOfTeleportation.class,  2);
			
			stones.put(ScrollOfTerror.class,        StoneOfAffection.class);
			amnts.put(ScrollOfTerror.class,         3);
			
			stones.put(ScrollOfTransmutation.class, StoneOfAugmentation.class);
			amnts.put(ScrollOfTransmutation.class,  2);
			
			stones.put(ScrollOfUpgrade.class,       StoneOfEnchantment.class);
			amnts.put(ScrollOfUpgrade.class,        2);
		}
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1
					|| !(ingredients.get(0) instanceof Scroll)
					|| !stones.containsKey(ingredients.get(0).getClass())){
				return false;
			}
			
			return true;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			Scroll s = (Scroll) ingredients.get(0);
			
			s.quantity(s.quantity() - 1);
			
			try{
				return stones.get(s.getClass()).newInstance().quantity(amnts.get(s.getClass()));
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			try{
				Scroll s = (Scroll) ingredients.get(0);
				return stones.get(s.getClass()).newInstance().quantity(amnts.get(s.getClass()));
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
	}
}
