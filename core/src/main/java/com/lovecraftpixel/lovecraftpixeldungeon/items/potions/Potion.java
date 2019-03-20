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

package com.lovecraftpixel.lovecraftpixeldungeon.items.potions;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Challenges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.Statistics;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Fire;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Ooze;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Splash;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.ItemStatusHandler;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.bags.Bag;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.ExoticPotion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfCorrosiveGas;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.lovecraftpixel.lovecraftpixeldungeon.journal.Catalog;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Apricobush;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Blackholeflower;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Blindweed;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Butterlion;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Chandaliertail;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Chillisnapper;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Crimsonpepper;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Dreamfoil;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Earthroot;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Fadeleaf;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Firebloom;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Firefoxglove;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Frostcorn;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Grasslilly;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Icecap;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Kiwivetch;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Musclemoss;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Nightshadeonion;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Parasiteshrub;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Peanutpetal;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Rose;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Rotberry;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Snowhedge;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Sorrowmoss;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Starflower;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Steamweed;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Stormvine;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Sunbloom;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Suncarnivore;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Sungrass;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Swiftthistle;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Tomatobush;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Venusflytrap;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Waterweed;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Willowcane;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Witherfennel;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndBag;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndItem;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Potion extends Item {

	public static final String AC_DRINK = "DRINK";
	
	//used internally for potions that can be drunk or thrown
	public static final String AC_CHOOSE = "CHOOSE";

	private static final float TIME_TO_DRINK = 1f;

	protected Integer initials;

	private static final Class<?>[] potions = {
			PotionOfHealing.class,
			PotionOfExperience.class,
			PotionOfToxicGas.class,
			PotionOfLiquidFlame.class,
			PotionOfStrength.class,
			PotionOfParalyticGas.class,
			PotionOfLevitation.class,
			PotionOfMindVision.class,
			PotionOfPurity.class,
			PotionOfInvisibility.class,
			PotionOfHaste.class,
			PotionOfFrost.class
	};

	private static final HashMap<String, Integer> colors = new HashMap<String, Integer>() {
		{
			put("crimson",ItemSpriteSheet.POTION_CRIMSON);
			put("amber",ItemSpriteSheet.POTION_AMBER);
			put("golden",ItemSpriteSheet.POTION_GOLDEN);
			put("jade",ItemSpriteSheet.POTION_JADE);
			put("turquoise",ItemSpriteSheet.POTION_TURQUOISE);
			put("azure",ItemSpriteSheet.POTION_AZURE);
			put("indigo",ItemSpriteSheet.POTION_INDIGO);
			put("magenta",ItemSpriteSheet.POTION_MAGENTA);
			put("bistre",ItemSpriteSheet.POTION_BISTRE);
			put("charcoal",ItemSpriteSheet.POTION_CHARCOAL);
			put("silver",ItemSpriteSheet.POTION_SILVER);
			put("ivory",ItemSpriteSheet.POTION_IVORY);
            put("yellow",ItemSpriteSheet.POTION_YELLOW);
            put("white",ItemSpriteSheet.POTION_WHITE);
            put("brown",ItemSpriteSheet.POTION_BROWN);
            put("brightblue",ItemSpriteSheet.POTION_BRIGHTBLUE);
            put("rainbow",ItemSpriteSheet.POTION_RAINBOW);
            put("brightorange",ItemSpriteSheet.POTION_BRIGHTORANGE);
            put("brightblue",ItemSpriteSheet.POTION_DARKBLUE);
            put("black",ItemSpriteSheet.POTION_BLACK);
            put("blue",ItemSpriteSheet.POTION_BLUE);
            put("flatblue",ItemSpriteSheet.POTION_FLATBLUE);
            put("parasitic",ItemSpriteSheet.POTION_PARASITIC);
            put("diarrhoea",ItemSpriteSheet.POTION_DIARRHOEA);
            put("punch",ItemSpriteSheet.POTION_PUNCH);
            put("beige",ItemSpriteSheet.POTION_BEIGE);
            put("bloodypoop",ItemSpriteSheet.POTION_BLOODY_POOP);
            put("waterblue",ItemSpriteSheet.POTION_WATERBLUE);
            put("brightgreen",ItemSpriteSheet.POTION_BRIGHT_GREEN);
            put("indigopurple",ItemSpriteSheet.POTION_INDIGO_PURPLE);
            put("limegreen",ItemSpriteSheet.POTION_LIMEGREEN);
            put("rose",ItemSpriteSheet.POTION_ROSE);
            put("brightpurple",ItemSpriteSheet.POTION_BRIGHT_PURPLE);
            put("darkrose",ItemSpriteSheet.POTION_DARK_ROSE);
            put("honey",ItemSpriteSheet.POTION_HONEY);
            put("bloody",ItemSpriteSheet.POTION_BLOODY);
            put("orange",ItemSpriteSheet.POTION_ORANGE);
            put("violett",ItemSpriteSheet.POTION_VIOLETT);
		}
	};
	
	private static final HashSet<Class<?extends Potion>> mustThrowPots = new HashSet<>();
	static{
		mustThrowPots.add(PotionOfToxicGas.class);
		mustThrowPots.add(PotionOfLiquidFlame.class);
		mustThrowPots.add(PotionOfParalyticGas.class);
		mustThrowPots.add(PotionOfFrost.class);
		
		//exotic
		mustThrowPots.add(PotionOfCorrosiveGas.class);
		mustThrowPots.add(PotionOfSnapFreeze.class);
		mustThrowPots.add(PotionOfShroudingFog.class);
		mustThrowPots.add(PotionOfStormClouds.class);
		
		//also all brews, hardcoded
	}
	
	private static final HashSet<Class<?extends Potion>> canThrowPots = new HashSet<>();
	static{
		canThrowPots.add(PotionOfPurity.class);
		canThrowPots.add(PotionOfLevitation.class);
		
		//exotic
		canThrowPots.add(PotionOfCleansing.class);
		
		//elixirs
		canThrowPots.add(ElixirOfHoneyedHealing.class);
	}
	
	protected static ItemStatusHandler<Potion> handler;
	
	protected String color;
	
	{
		stackable = true;
		defaultAction = AC_DRINK;
	}
	
	@SuppressWarnings("unchecked")
	public static void initColors() {
		handler = new ItemStatusHandler<>( (Class<? extends Potion>[])potions, colors );
	}
	
	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		ArrayList<Class<?extends Item>> classes = new ArrayList<>();
		for (Item i : items){
			if (i instanceof ExoticPotion){
				if (!classes.contains(ExoticPotion.exoToReg.get(i.getClass()))){
					classes.add(ExoticPotion.exoToReg.get(i.getClass()));
				}
			} else if (i instanceof Potion){
				if (!classes.contains(i.getClass())){
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively( bundle, classes );
	}
	
	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Potion>[])potions, colors, bundle );
	}
	
	public Potion() {
		super();
		reset();
	}
	
	//anonymous potions are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = ItemSpriteSheet.POTION_HOLDER;
		anonymous = true;
	}

	@Override
	public void reset(){
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			color = handler.label(this);
		}
		setAction();
	}
	
	@Override
	public boolean collect( Bag container ) {
		if (super.collect( container )){
			setAction();
			return true;
		} else {
			return false;
		}
	}
	
	public void setAction(){
		if (isKnown() && mustThrowPots.contains(this.getClass())) {
			defaultAction = AC_THROW;
		} else if (isKnown() &&canThrowPots.contains(this.getClass())){
			defaultAction = AC_CHOOSE;
		} else {
			defaultAction = AC_DRINK;
		}
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_DRINK );
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals( AC_CHOOSE )){
			
			GameScene.show(new WndItem(null, this, true) );
			
		} else if (action.equals( AC_DRINK )) {
			
			if (isKnown() && mustThrowPots.contains(getClass())) {
				
					GameScene.show(
						new WndOptions( Messages.get(Potion.class, "harmful"),
								Messages.get(Potion.class, "sure_drink"),
								Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no") ) {
							@Override
							protected void onSelect(int index) {
								if (index == 0) {
									drink( hero );
								}
							};
						}
					);
					
				} else {
					drink( hero );
				}
			
		}
	}
	
	@Override
	public void doThrow( final Hero hero ) {

		if (isKnown()
				&& !mustThrowPots.contains(this.getClass())
				&& !canThrowPots.contains(this.getClass())) {
		
			GameScene.show(
				new WndOptions( Messages.get(Potion.class, "beneficial"),
						Messages.get(Potion.class, "sure_throw"),
						Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no") ) {
					@Override
					protected void onSelect(int index) {
						if (index == 0) {
							Potion.super.doThrow( hero );
						}
					};
				}
			);
			
		} else {
			super.doThrow( hero );
		}
	}
	
	protected void drink( Hero hero ) {
		
		detach( hero.belongings.backpack );
		
		hero.spend( TIME_TO_DRINK );
		hero.busy();
		apply( hero );
		
		Sample.INSTANCE.play( Assets.SND_DRINK );
		
		hero.sprite.operate( hero.pos );
	}
	
	@Override
	protected void onThrow( int cell ) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {
			
			super.onThrow( cell );
			
		} else  {

			Dungeon.level.press( cell, null, true );
			shatter( cell );
			
		}
	}
	
	public void apply( Hero hero ) {
		shatter( hero.pos );
	}
	
	public void shatter( int cell ) {
		if (Dungeon.level.heroFOV[cell]) {
			GLog.i( Messages.get(Potion.class, "shatter") );
			Sample.INSTANCE.play( Assets.SND_SHATTER );
			splash( cell );
		}
	}

	@Override
	public void cast( final Hero user, int dst ) {
			super.cast(user, dst);
	}
	
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}
	
	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
				Potion p = Dungeon.hero.belongings.getItem(getClass());
				if (p != null)  p.setAction();
				if (ExoticPotion.regToExo.get(getClass()) != null) {
					p = Dungeon.hero.belongings.getItem(ExoticPotion.regToExo.get(getClass()));
					if (p != null) p.setAction();
				}
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
		return isKnown() ? super.name() : Messages.get(this, color);
	}
	
	@Override
	public String info() {
		return isKnown() ? desc() : Messages.get(this, "unknown_desc");
	}

	public Integer initials(){
		return isKnown() ? initials : null;
	}
	
	@Override
	public boolean isIdentified() {
		return isKnown();
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	public static HashSet<Class<? extends Potion>> getKnown() {
		return handler.known();
	}
	
	public static HashSet<Class<? extends Potion>> getUnknown() {
		return handler.unknown();
	}
	
	public static boolean allKnown() {
		return handler.known().size() == potions.length;
	}
	
	protected int splashColor(){
		return ItemSprite.pick( image, 5, 9 );
	}
	
	protected void splash( int cell ) {

		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		if (fire != null)
			fire.clear( cell );

		final int color = splashColor();

		Char ch = Actor.findChar(cell);
		if (ch != null) {
			Buff.detach(ch, Burning.class);
			Buff.detach(ch, Ooze.class);
			Splash.at( ch.sprite.center(), color, 5 );
		} else {
			Splash.at( cell, color, 5 );
		}
	}
	
	@Override
	public int price() {
		return 30 * quantity;
	}
	
	
	public static class SeedToPotion extends Recipe {
		
		public static HashMap<Class<?extends Plant.Seed>, Class<?extends Potion>> types = new HashMap<>();
		static {
			types.put(Blindweed.Seed.class,         PotionOfInvisibility.class);
			types.put(Dreamfoil.Seed.class,         PotionOfPurity.class);
			types.put(Earthroot.Seed.class,         PotionOfParalyticGas.class);
			types.put(Fadeleaf.Seed.class,          PotionOfMindVision.class);
			types.put(Firebloom.Seed.class,         PotionOfLiquidFlame.class);
			types.put(Icecap.Seed.class,            PotionOfFrost.class);
			types.put(Rotberry.Seed.class,          PotionOfStrength.class);
			types.put(Sorrowmoss.Seed.class,        PotionOfToxicGas.class);
			types.put(Starflower.Seed.class,        PotionOfExperience.class);
			types.put(Stormvine.Seed.class,         PotionOfLevitation.class);
			types.put(Sungrass.Seed.class,          PotionOfHealing.class);
			types.put(Swiftthistle.Seed.class,      PotionOfHaste.class);
			//TODO: Add the new potions
            types.put(Apricobush.Seed.class,        PotionOfHealing.class);
            types.put(Blackholeflower.Seed.class,   PotionOfHealing.class);
            types.put(Butterlion.Seed.class,        PotionOfHealing.class);
            types.put(Chandaliertail.Seed.class,    PotionOfHealing.class);
            types.put(Chillisnapper.Seed.class,     PotionOfHealing.class);
            types.put(Crimsonpepper.Seed.class,     PotionOfHealing.class);
            types.put(Firefoxglove.Seed.class,      PotionOfHealing.class);
            types.put(Frostcorn.Seed.class,         PotionOfHealing.class);
            types.put(Grasslilly.Seed.class,        PotionOfHealing.class);
            types.put(Kiwivetch.Seed.class,         PotionOfHealing.class);
            types.put(Musclemoss.Seed.class,        PotionOfHealing.class);
            types.put(Nightshadeonion.Seed.class,   PotionOfHealing.class);
            types.put(Parasiteshrub.Seed.class,     PotionOfHealing.class);
            types.put(Peanutpetal.Seed.class,       PotionOfHealing.class);
            types.put(Rose.Seed.class,              PotionOfHealing.class);
            types.put(Snowhedge.Seed.class,         PotionOfHealing.class);
            types.put(Steamweed.Seed.class,         PotionOfHealing.class);
            types.put(Sunbloom.Seed.class,          PotionOfHealing.class);
            types.put(Suncarnivore.Seed.class,      PotionOfHealing.class);
            types.put(Tomatobush.Seed.class,        PotionOfHealing.class);
            types.put(Venusflytrap.Seed.class,      PotionOfHealing.class);
            types.put(Waterweed.Seed.class,         PotionOfHealing.class);
            types.put(Willowcane.Seed.class,        PotionOfHealing.class);
            types.put(Witherfennel.Seed.class,      PotionOfHealing.class);
		}
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 3) {
				return false;
			}
			
			for (Item ingredient : ingredients){
				if (!(ingredient instanceof Plant.Seed
						&& ingredient.quantity() >= 1
						&& types.containsKey(ingredient.getClass()))){
					return false;
				}
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
			
			for (Item ingredient : ingredients){
				ingredient.quantity(ingredient.quantity() - 1);
			}
			
			ArrayList<Class<?extends Plant.Seed>> seeds = new ArrayList<>();
			for (Item i : ingredients) {
				if (!seeds.contains(i.getClass())) {
					seeds.add((Class<? extends Plant.Seed>) i.getClass());
				}
			}
			
			Item result;
			
			if ( (seeds.size() == 2 && Random.Int(4) == 0)
					|| (seeds.size() == 3 && Random.Int(2) == 0)) {
				
				result = Generator.random( Generator.Category.POTION );
				
			} else {
				
				Class<? extends Potion> itemClass = types.get(Random.element(ingredients).getClass());
				try {
					result = itemClass.newInstance();
				} catch (Exception e) {
					LovecraftPixelDungeon.reportException(e);
					result = Generator.random( Generator.Category.POTION );
				}
				
			}
			
			while (result instanceof PotionOfHealing
					&& (Dungeon.isChallenged(Challenges.NO_HEALING)
					|| Random.Int(10) < Dungeon.LimitedDrops.COOKING_HP.count)) {
				result = Generator.random(Generator.Category.POTION);
			}
			
			if (result instanceof PotionOfHealing) {
				Dungeon.LimitedDrops.COOKING_HP.count++;
			}
			
			Statistics.potionsCooked++;
			Badges.validatePotionsCooked();
			
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new WndBag.Placeholder(ItemSpriteSheet.POTION_HOLDER){
				{
					name = Messages.get(SeedToPotion.class, "name");
				}
				
				@Override
				public String info() {
					return "";
				}
			};
		}
	}
}
