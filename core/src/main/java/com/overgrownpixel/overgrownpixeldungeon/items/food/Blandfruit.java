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

package com.overgrownpixel.overgrownpixeldungeon.items.food;

import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfExperience;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfFrost;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHaste;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfInvisibility;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLevitation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfMindVision;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfParalyticGas;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfPurity;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfStrength;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfToxicGas;
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
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant.Seed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sungrass;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Blandfruit extends Food {

	public Potion potionAttrib = null;
	public ItemSprite.Glowing potionGlow = null;

	{
		stackable = true;
		image = ItemSpriteSheet.BLANDFRUIT;

		//only applies when blandfruit is cooked
		energy = Hunger.STARVING;

		bones = true;
	}

	@Override
	public boolean isSimilar( Item item ) {
		if ( super.isSimilar(item) ){
			Blandfruit other = (Blandfruit) item;
			if (potionAttrib == null && other.potionAttrib == null) {
					return true;
			} else if (potionAttrib != null && other.potionAttrib != null
					&& potionAttrib.isSimilar(other.potionAttrib)){
					return true;
			}
		}
		return false;
	}

	@Override
	public void execute( Hero hero, String action ) {

		if (action.equals( AC_EAT ) && potionAttrib == null) {

			GLog.w( Messages.get(this, "raw"));
			return;

		}

		super.execute(hero, action);

		if (action.equals( AC_EAT ) && potionAttrib != null){

			potionAttrib.apply(hero);

		}
	}

	@Override
	public String desc() {
		if (potionAttrib== null) {
			return super.desc();
		} else {
			String desc = Messages.get(this, "desc_cooked") + "\n\n";
			if (potionAttrib instanceof PotionOfFrost
				|| potionAttrib instanceof PotionOfLiquidFlame
				|| potionAttrib instanceof PotionOfToxicGas
				|| potionAttrib instanceof PotionOfParalyticGas) {
				desc += Messages.get(this, "desc_throw");
			} else {
				desc += Messages.get(this, "desc_eat");
			}
			return desc;
		}
	}

	@Override
	public int price() {
		return 20 * quantity;
	}

	public Item cook(Seed seed){

		try {
			return imbuePotion(Potion.SeedToPotion.types.get(seed.getClass()).newInstance());
		} catch (Exception e) {
			OvergrownPixelDungeon.reportException(e);
			return null;
		}

	}

	public Item imbuePotion(Potion potion){

		potionAttrib = potion;
		potionAttrib.anonymize();

		potionAttrib.image = ItemSpriteSheet.BLANDFRUIT;

		if (potionAttrib instanceof PotionOfHealing){
			name = Messages.get(this, "sunfruit");
			potionGlow = new ItemSprite.Glowing( 0x2EE62E );
		} else if (potionAttrib instanceof PotionOfStrength){
			name = Messages.get(this, "rotfruit");
			potionGlow = new ItemSprite.Glowing( 0xCC0022 );
		} else if (potionAttrib instanceof PotionOfParalyticGas){
			name = Messages.get(this, "earthfruit");
			potionGlow = new ItemSprite.Glowing( 0x67583D );
		} else if (potionAttrib instanceof PotionOfInvisibility){
			name = Messages.get(this, "blindfruit");
			potionGlow = new ItemSprite.Glowing( 0xD9D9D9 );
		} else if (potionAttrib instanceof PotionOfLiquidFlame){
			name = Messages.get(this, "firefruit");
			potionGlow = new ItemSprite.Glowing( 0xFF7F00 );
		} else if (potionAttrib instanceof PotionOfFrost){
			name = Messages.get(this, "icefruit");
			potionGlow = new ItemSprite.Glowing( 0x66B3FF );
		} else if (potionAttrib instanceof PotionOfMindVision){
			name = Messages.get(this, "fadefruit");
			potionGlow = new ItemSprite.Glowing( 0x919999 );
		} else if (potionAttrib instanceof PotionOfToxicGas){
			name = Messages.get(this, "sorrowfruit");
			potionGlow = new ItemSprite.Glowing( 0xA15CE5 );
		} else if (potionAttrib instanceof PotionOfLevitation) {
			name = Messages.get(this, "stormfruit");
			potionGlow = new ItemSprite.Glowing( 0x1B5F79 );
		} else if (potionAttrib instanceof PotionOfPurity) {
			name = Messages.get(this, "dreamfruit");
			potionGlow = new ItemSprite.Glowing( 0xC152AA );
		} else if (potionAttrib instanceof PotionOfExperience) {
			name = Messages.get(this, "starfruit");
			potionGlow = new ItemSprite.Glowing( 0x404040 );
		} else if (potionAttrib instanceof PotionOfHaste) {
			name = Messages.get(this, "swiftfruit");
			potionGlow = new ItemSprite.Glowing( 0xCCBB00 );
		}

        else if (potionAttrib instanceof PotionOfDarkness) {
            name = Messages.get(this, "darkfruit");
            potionGlow = new ItemSprite.Glowing( 0x23289B );
        } else if (potionAttrib instanceof PotionOfDisease) {
            name = Messages.get(this, "illfruit");
            potionGlow = new ItemSprite.Glowing( 0xFFFFFF );
        } else if (potionAttrib instanceof PotionOfEruption) {
            name = Messages.get(this, "earthquakefruit");
            potionGlow = new ItemSprite.Glowing( 0xFFC633 );
        } else if (potionAttrib instanceof PotionOfExplosion) {
            name = Messages.get(this, "explosionfruit");
            potionGlow = new ItemSprite.Glowing( 0xB31E00 );
        } else if (potionAttrib instanceof PotionOfFirestorm) {
            name = Messages.get(this, "firestormfruit");
            potionGlow = new ItemSprite.Glowing( 0xD4561D );
        } else if (potionAttrib instanceof PotionOfFood) {
            name = Messages.get(this, "foodfruit");
            potionGlow = new ItemSprite.Glowing( 0x6045E5 );
        } else if (potionAttrib instanceof PotionOfGlowing) {
            name = Messages.get(this, "glowingfruit");
            potionGlow = new ItemSprite.Glowing( 0xFF8C00 );
        } else if (potionAttrib instanceof PotionOfHealth) {
            name = Messages.get(this, "healthfruit");
            potionGlow = new ItemSprite.Glowing( 0xDC4287 );
        } else if (potionAttrib instanceof PotionOfHeat) {
            name = Messages.get(this, "heatfruit");
            potionGlow = new ItemSprite.Glowing( 0x850D0D );
        } else if (potionAttrib instanceof PotionOfIce) {
            name = Messages.get(this, "frostfruit");
            potionGlow = new ItemSprite.Glowing( 0x0195AC );
        } else if (potionAttrib instanceof PotionOfLight) {
            name = Messages.get(this, "lightfruit");
            potionGlow = new ItemSprite.Glowing( 0xF62020 );
        } else if (potionAttrib instanceof PotionOfMuscle) {
            name = Messages.get(this, "musclefruit");
            potionGlow = new ItemSprite.Glowing( 0xBA00E0 );
        } else if (potionAttrib instanceof PotionOfPlants) {
            name = Messages.get(this, "faunafruit");
            potionGlow = new ItemSprite.Glowing( 0x22A20B );
        } else if (potionAttrib instanceof PotionOfPower) {
            name = Messages.get(this, "powerfruit");
            potionGlow = new ItemSprite.Glowing( 0xD2791C );
        } else if (potionAttrib instanceof PotionOfRain) {
            name = Messages.get(this, "rainfruit");
            potionGlow = new ItemSprite.Glowing( 0x4369B0 );
        } else if (potionAttrib instanceof PotionOfRegrowth) {
            name = Messages.get(this, "regrowthfruit");
            potionGlow = new ItemSprite.Glowing( 0xAFD015 );
        } else if (potionAttrib instanceof PotionOfSecretion) {
            name = Messages.get(this, "secretionfruit");
            potionGlow = new ItemSprite.Glowing( 0x85277D );
        } else if (potionAttrib instanceof PotionOfSpirit) {
            name = Messages.get(this, "spiritfruit");
            potionGlow = new ItemSprite.Glowing( 0xF15959 );
        } else if (potionAttrib instanceof PotionOfSpores) {
            name = Messages.get(this, "sporesfruit");
            potionGlow = new ItemSprite.Glowing( 0xAC00AE );
        } else if (potionAttrib instanceof PotionOfSteam) {
            name = Messages.get(this, "steamfruit");
            potionGlow = new ItemSprite.Glowing( 0xFFD400 );
        } else if (potionAttrib instanceof PotionOfSunlight) {
            name = Messages.get(this, "sunlightfruit");
            potionGlow = new ItemSprite.Glowing( 0xBFBD00 );
        } else if (potionAttrib instanceof PotionOfTeleportation) {
            name = Messages.get(this, "teleportfruit");
            potionGlow = new ItemSprite.Glowing( 0x303130 );
        } else if (potionAttrib instanceof PotionOfTime) {
            name = Messages.get(this, "timefruit");
            potionGlow = new ItemSprite.Glowing( 0x417BCE );
        }

		return this;
	}

	public static final String POTIONATTRIB = "potionattrib";
	
	@Override
	protected void onThrow(int cell) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {
			super.onThrow( cell );
			
		} else if (potionAttrib instanceof PotionOfLiquidFlame ||
				potionAttrib instanceof PotionOfToxicGas ||
				potionAttrib instanceof PotionOfParalyticGas ||
				potionAttrib instanceof PotionOfFrost ||
				potionAttrib instanceof PotionOfLevitation ||
				potionAttrib instanceof PotionOfPurity) {

			potionAttrib.shatter( cell );
			Dungeon.level.drop(new Chunks(), cell).sprite.drop();
			
		} else {
			super.onThrow( cell );
		}
	}
	
	@Override
	public void reset() {
		if (potionAttrib != null)
			imbuePotion(potionAttrib);
		else
			super.reset();
	}
	
	@Override
	public void storeInBundle(Bundle bundle){
		super.storeInBundle(bundle);
		bundle.put( POTIONATTRIB , potionAttrib);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(POTIONATTRIB)) {
			imbuePotion((Potion) bundle.get(POTIONATTRIB));
		}
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return potionGlow;
	}
	
	public static class CookFruit extends Recipe {
		
		@Override
		//also sorts ingredients if it can
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;
			
			if (ingredients.get(0) instanceof Blandfruit){
				if (!(ingredients.get(1) instanceof Seed)){
					return false;
				}
			} else if (ingredients.get(0) instanceof Seed){
				if (ingredients.get(1) instanceof Blandfruit){
					Item temp = ingredients.get(0);
					ingredients.set(0, ingredients.get(1));
					ingredients.set(1, temp);
				} else {
					return false;
				}
			} else {
				return false;
			}
			
			Blandfruit fruit = (Blandfruit) ingredients.get(0);
			Seed seed = (Seed) ingredients.get(1);
			
			if (fruit.quantity() >= 1 && fruit.potionAttrib == null
				&& seed.quantity() >= 1){

				if (Dungeon.isChallenged(Challenges.NO_HEALING)
						&& seed instanceof Sungrass.Seed){
					return false;
				}

				return true;
			}
			
			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 3;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			ingredients.get(0).quantity(ingredients.get(0).quantity() - 1);
			ingredients.get(1).quantity(ingredients.get(1).quantity() - 1);
			
			
			return new Blandfruit().cook((Seed) ingredients.get(1));
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			return new Blandfruit().cook((Seed) ingredients.get(1));
		}
	}

	public static class Chunks extends Food {

		{
			stackable = true;
			image = ItemSpriteSheet.BLAND_CHUNKS;

			energy = Hunger.STARVING;

			bones = true;
		}

	}

}
