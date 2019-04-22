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

package com.overgrownpixel.overgrownpixeldungeon.items;

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Blandfruit;
import com.overgrownpixel.overgrownpixeldungeon.items.food.MeatPie;
import com.overgrownpixel.overgrownpixeldungeon.items.food.StewedMeat;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.BlizzardBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.CausticBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.FrigidBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.FrostfireBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.InfernalBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.ShockingBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.brews.WickedBrew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfIcyTouch;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfMight;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfRestoration;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfToxicEssence;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfVitality;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.ExoticPotion;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.Scroll;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.Alchemize;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.AquaBlast;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.BeaconOfReturning;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.CrimsonEpithet;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.CurseInfusion;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.DoomCall;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.EnchantmentInfusion;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.FeatherFall;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.ForcePush;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.Forcefield;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.HolyBlast;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.MagicalInfusion;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.MagicalPorter;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.NaturesLullaby;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.PhaseShift;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.PlantSummon;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.ReclaimTrap;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.Recycle;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.SeasonChange;
import com.overgrownpixel.overgrownpixeldungeon.items.spells.SpontaneosCombustion;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.Wand;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.Dart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TippedDart;

import java.util.ArrayList;

public abstract class Recipe {
	
	public abstract boolean testIngredients(ArrayList<Item> ingredients);
	
	public abstract int cost(ArrayList<Item> ingredients);
	
	public abstract Item brew(ArrayList<Item> ingredients);
	
	public abstract Item sampleOutput(ArrayList<Item> ingredients);
	
	//subclass for the common situation of a recipe with static inputs and outputs
	public static abstract class SimpleRecipe extends Recipe {
		
		//*** These elements must be filled in by subclasses
		protected Class<?extends Item>[] inputs; //each class should be unique
		protected int[] inQuantity;
		
		protected int cost;
		
		protected Class<?extends Item> output;
		protected int outQuantity;
		//***
		
		//gets a simple list of items based on inputs
		public ArrayList<Item> getIngredients() {
			ArrayList<Item> result = new ArrayList<>();
			try {
				for (int i = 0; i < inputs.length; i++) {
					Item ingredient = inputs[i].newInstance();
					ingredient.quantity(inQuantity[i]);
					result.add(ingredient);
				}
			} catch (Exception e){
				OvergrownPixelDungeon.reportException( e );
				return null;
			}
			return result;
		}
		
		@Override
		public final boolean testIngredients(ArrayList<Item> ingredients) {
			
			int[] needed = inQuantity.clone();
			
			for (Item ingredient : ingredients){
				for (int i = 0; i < inputs.length; i++){
					if (ingredient.getClass() == inputs[i]){
						needed[i] -= ingredient.quantity();
						break;
					}
				}
			}
			
			for (int i : needed){
				if (i > 0){
					return false;
				}
			}
			
			return true;
		}
		
		public final int cost(ArrayList<Item> ingredients){
			return cost;
		}
		
		@Override
		public final Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			int[] needed = inQuantity.clone();
			
			for (Item ingredient : ingredients){
				for (int i = 0; i < inputs.length; i++) {
					if (ingredient.getClass() == inputs[i] && needed[i] > 0) {
						if (needed[i] <= ingredient.quantity()) {
							ingredient.quantity(ingredient.quantity() - needed[i]);
							needed[i] = 0;
						} else {
							needed[i] -= ingredient.quantity();
							ingredient.quantity(0);
						}
					}
				}
			}
			
			//sample output and real output are identical in this case.
			return sampleOutput(null);
		}
		
		//ingredients are ignored, as output doesn't vary
		public final Item sampleOutput(ArrayList<Item> ingredients){
			try {
				Item result = output.newInstance();
				result.quantity(outQuantity);
				return result;
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException( e );
				return null;
			}
		}
	}
	
	
	//*******
	// Static members
	//*******
	
	private static Recipe[] oneIngredientRecipes = new Recipe[]{
		new AlchemistsToolkit.upgradeKit(),
		new Scroll.ScrollToStone(),
		new StewedMeat.oneMeat()
	};
	
	private static Recipe[] twoIngredientRecipes = new Recipe[]{
		new Blandfruit.CookFruit(),
		new TippedDart.TipDart(),
		new Bomb.EnhanceBomb(),
		new ElixirOfAquaticRejuvenation.Recipe(),
		new ElixirOfDragonsBlood.Recipe(),
		new ElixirOfIcyTouch.Recipe(),
		new ElixirOfMight.Recipe(),
		new ElixirOfHoneyedHealing.Recipe(),
		new ElixirOfRestoration.Recipe(),
		new ElixirOfToxicEssence.Recipe(),
		new ElixirOfVitality.Recipe(),
		new BlizzardBrew.Recipe(),
		new CausticBrew.Recipe(),
		new FrigidBrew.Recipe(),
		new FrostfireBrew.Recipe(),
		new InfernalBrew.Recipe(),
		new ShockingBrew.Recipe(),
		new WickedBrew.Recipe(),
		new Alchemize.Recipe(),
		new AquaBlast.Recipe(),
		new BeaconOfReturning.Recipe(),
		new CurseInfusion.Recipe(),
		new FeatherFall.Recipe(),
		new MagicalInfusion.Recipe(),
		new MagicalPorter.Recipe(),
		new PhaseShift.Recipe(),
		new ReclaimTrap.Recipe(),
		new Recycle.Recipe(),
		new StewedMeat.twoMeat(),
            new EnchantmentInfusion.Recipe(),
            new CrimsonEpithet.Recipe(),
            new Forcefield.Recipe(),
            new HolyBlast.Recipe(),
            new DoomCall.Recipe(),
            new SeasonChange.Recipe(),
            new PlantSummon.Recipe(),
            new NaturesLullaby.Recipe(),
            new SpontaneosCombustion.Recipe(),
            new ForcePush.Recipe(),
	};
	
	private static Recipe[] threeIngredientRecipes = new Recipe[]{
		new Potion.SeedToPotion(),
		new ExoticPotion.PotionToExotic(),
		new ExoticScroll.ScrollToExotic(),
		new StewedMeat.threeMeat(),
		new MeatPie.Recipe()
	};
	
	public static Recipe findRecipe(ArrayList<Item> ingredients){
		
		if (ingredients.size() == 1){
			for (Recipe recipe : oneIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					return recipe;
				}
			}
			
		} else if (ingredients.size() == 2){
			for (Recipe recipe : twoIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					return recipe;
				}
			}
			
		} else if (ingredients.size() == 3){
			for (Recipe recipe : threeIngredientRecipes){
				if (recipe.testIngredients(ingredients)){
					return recipe;
				}
			}
		}
		
		return null;
	}
	
	public static boolean usableInRecipe(Item item){
		return item.isIdentified()
				&& !item.cursed
				&& (!(item instanceof EquipableItem) || item instanceof Dart || item instanceof AlchemistsToolkit)
				&& !(item instanceof Wand);
	}
}


