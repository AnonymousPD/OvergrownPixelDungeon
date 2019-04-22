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

package com.overgrownpixel.overgrownpixeldungeon.items.potions;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.Statistics;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Burning;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Ooze;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Splash;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.ItemStatusHandler;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.Bag;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfDarkness;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfDisease;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfEruption;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfExplosion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfFirestorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfFood;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfGlowing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfHealth;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfHeat;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfIce;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfLight;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfMuscle;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfPlants;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfPower;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfRain;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfSecretion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfSpirit;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfSpores;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfSteam;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfSunlight;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfTime;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.ExoticPotion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfCorrosiveGas;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.overgrownpixel.overgrownpixeldungeon.journal.Catalog;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Apricobush;
import com.overgrownpixel.overgrownpixeldungeon.plants.Blackholeflower;
import com.overgrownpixel.overgrownpixeldungeon.plants.Blindweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Butterlion;
import com.overgrownpixel.overgrownpixeldungeon.plants.Chandaliertail;
import com.overgrownpixel.overgrownpixeldungeon.plants.Chillisnapper;
import com.overgrownpixel.overgrownpixeldungeon.plants.Crimsonpepper;
import com.overgrownpixel.overgrownpixeldungeon.plants.Dreamfoil;
import com.overgrownpixel.overgrownpixeldungeon.plants.Earthroot;
import com.overgrownpixel.overgrownpixeldungeon.plants.Fadeleaf;
import com.overgrownpixel.overgrownpixeldungeon.plants.Firebloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Firefoxglove;
import com.overgrownpixel.overgrownpixeldungeon.plants.Frostcorn;
import com.overgrownpixel.overgrownpixeldungeon.plants.Grasslilly;
import com.overgrownpixel.overgrownpixeldungeon.plants.Icecap;
import com.overgrownpixel.overgrownpixeldungeon.plants.Kiwivetch;
import com.overgrownpixel.overgrownpixeldungeon.plants.Musclemoss;
import com.overgrownpixel.overgrownpixeldungeon.plants.Nightshadeonion;
import com.overgrownpixel.overgrownpixeldungeon.plants.Parasiteshrub;
import com.overgrownpixel.overgrownpixeldungeon.plants.Peanutpetal;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.plants.Rose;
import com.overgrownpixel.overgrownpixeldungeon.plants.Rotberry;
import com.overgrownpixel.overgrownpixeldungeon.plants.Snowhedge;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sorrowmoss;
import com.overgrownpixel.overgrownpixeldungeon.plants.Starflower;
import com.overgrownpixel.overgrownpixeldungeon.plants.Steamweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Stormvine;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sunbloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Suncarnivore;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sungrass;
import com.overgrownpixel.overgrownpixeldungeon.plants.Swiftthistle;
import com.overgrownpixel.overgrownpixeldungeon.plants.Tomatobush;
import com.overgrownpixel.overgrownpixeldungeon.plants.Venusflytrap;
import com.overgrownpixel.overgrownpixeldungeon.plants.Waterweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Willowcane;
import com.overgrownpixel.overgrownpixeldungeon.plants.Witherfennel;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndItem;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;
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
			PotionOfExperience.class,
            PotionOfFrost.class,
            PotionOfHaste.class,
            PotionOfHealing.class,
            PotionOfInvisibility.class,
            PotionOfLevitation.class,
            PotionOfLiquidFlame.class,
            PotionOfMindVision.class,
            PotionOfParalyticGas.class,
            PotionOfPurity.class,
            PotionOfStrength.class,
            PotionOfToxicGas.class,
            PotionOfDarkness.class,
            PotionOfDisease.class,
            PotionOfEruption.class,
            PotionOfExplosion.class,
            PotionOfFirestorm.class,
            PotionOfFood.class,
            PotionOfGlowing.class,
            PotionOfHealth.class,
            PotionOfHeat.class,
            PotionOfIce.class,
            PotionOfLight.class,
            PotionOfMuscle.class,
            PotionOfPlants.class,
            PotionOfPower.class,
            PotionOfRain.class,
            PotionOfRegrowth.class,
            PotionOfSecretion.class,
            PotionOfSpirit.class,
            PotionOfSpores.class,
            PotionOfSteam.class,
            PotionOfSunlight.class,
            PotionOfTeleportation.class,
            PotionOfTime.class
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
            put("darkblue",ItemSpriteSheet.POTION_DARKBLUE);
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

		//alchemy
        mustThrowPots.add(PotionOfDarkness.class);
        mustThrowPots.add(PotionOfEruption.class);
        mustThrowPots.add(PotionOfSteam.class);
        mustThrowPots.add(PotionOfSpores.class);
        mustThrowPots.add(PotionOfRegrowth.class);
        mustThrowPots.add(PotionOfRain.class);
        mustThrowPots.add(PotionOfIce.class);
		
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

		//alchemy
        canThrowPots.add(PotionOfExplosion.class);
        canThrowPots.add(PotionOfFirestorm.class);
        canThrowPots.add(PotionOfSunlight.class);
        canThrowPots.add(PotionOfSpirit.class);
        canThrowPots.add(PotionOfPlants.class);
        canThrowPots.add(PotionOfFood.class);
        canThrowPots.add(PotionOfHeat.class);
        canThrowPots.add(PotionOfDisease.class);
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

			types.put(Apricobush.Seed.class,        PotionOfHealth.class);
            types.put(Blackholeflower.Seed.class,   PotionOfTeleportation.class);
            types.put(Butterlion.Seed.class,        PotionOfEruption.class);
            types.put(Chandaliertail.Seed.class,    PotionOfGlowing.class);
            types.put(Chillisnapper.Seed.class,     PotionOfLight.class);
            types.put(Crimsonpepper.Seed.class,     PotionOfHeat.class);
            types.put(Firefoxglove.Seed.class,      PotionOfFirestorm.class);
            types.put(Frostcorn.Seed.class,         PotionOfIce.class);
            types.put(Grasslilly.Seed.class,        PotionOfPlants.class);
            types.put(Kiwivetch.Seed.class,         PotionOfRegrowth.class);
            types.put(Musclemoss.Seed.class,        PotionOfMuscle.class);
            types.put(Nightshadeonion.Seed.class,   PotionOfDarkness.class);
            types.put(Parasiteshrub.Seed.class,     PotionOfSpores.class);
            types.put(Peanutpetal.Seed.class,       PotionOfFood.class);
            types.put(Rose.Seed.class,              PotionOfSpirit.class);
            types.put(Snowhedge.Seed.class,         PotionOfDisease.class);
            types.put(Steamweed.Seed.class,         PotionOfSteam.class);
            types.put(Sunbloom.Seed.class,          PotionOfSunlight.class);
            types.put(Suncarnivore.Seed.class,      PotionOfSunlight.class);
            types.put(Tomatobush.Seed.class,        PotionOfExplosion.class);
            types.put(Venusflytrap.Seed.class,      PotionOfSecretion.class);
            types.put(Waterweed.Seed.class,         PotionOfRain.class);
            types.put(Willowcane.Seed.class,        PotionOfTime.class);
            types.put(Witherfennel.Seed.class,      PotionOfPower.class);
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
					OvergrownPixelDungeon.reportException(e);
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
