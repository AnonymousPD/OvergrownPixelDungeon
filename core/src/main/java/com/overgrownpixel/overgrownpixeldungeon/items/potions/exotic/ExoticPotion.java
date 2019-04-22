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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
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
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfBiomass;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfBlizzard;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfCataclysm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfEarthquake;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfEternalDarkness;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfFasting;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfHellstorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfHotSteam;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfInfernalHeat;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfInnerGlow;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfMutation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfNature;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfNumbness;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfRainStorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfRetribution;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfSpace;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfSuperBug;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfSuperHealth;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfSuperPower;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfSuperStrength;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfTimeStop;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfTorment;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy.PotionOfUltraviolet;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;

public class ExoticPotion extends Potion {
	
	{
		//sprite = equivalent potion sprite but one row down
	}
	
	public static final HashMap<Class<?extends Potion>, Class<?extends ExoticPotion>> regToExo = new HashMap<>();
	public static final HashMap<Class<?extends ExoticPotion>, Class<?extends Potion>> exoToReg = new HashMap<>();
	static{
		regToExo.put(PotionOfHealing.class, PotionOfShielding.class);
		exoToReg.put(PotionOfShielding.class, PotionOfHealing.class);
		
		regToExo.put(PotionOfToxicGas.class, PotionOfCorrosiveGas.class);
		exoToReg.put(PotionOfCorrosiveGas.class, PotionOfToxicGas.class);
		
		regToExo.put(PotionOfStrength.class, PotionOfAdrenalineSurge.class);
		exoToReg.put(PotionOfAdrenalineSurge.class, PotionOfStrength.class);
		
		regToExo.put(PotionOfFrost.class, PotionOfSnapFreeze.class);
		exoToReg.put(PotionOfSnapFreeze.class, PotionOfFrost.class);
		
		regToExo.put(PotionOfHaste.class, PotionOfStamina.class);
		exoToReg.put(PotionOfStamina.class, PotionOfHaste.class);
		
		regToExo.put(PotionOfLiquidFlame.class, PotionOfDragonsBreath.class);
		exoToReg.put(PotionOfDragonsBreath.class, PotionOfLiquidFlame.class);
		
		regToExo.put(PotionOfInvisibility.class, PotionOfShroudingFog.class);
		exoToReg.put(PotionOfShroudingFog.class, PotionOfInvisibility.class);
		
		regToExo.put(PotionOfMindVision.class, PotionOfMagicalSight.class);
		exoToReg.put(PotionOfMagicalSight.class, PotionOfMindVision.class);
		
		regToExo.put(PotionOfLevitation.class, PotionOfStormClouds.class);
		exoToReg.put(PotionOfStormClouds.class, PotionOfLevitation.class);
		
		regToExo.put(PotionOfExperience.class, PotionOfHolyFuror.class);
		exoToReg.put(PotionOfHolyFuror.class, PotionOfExperience.class);
		
		regToExo.put(PotionOfPurity.class, PotionOfCleansing.class);
		exoToReg.put(PotionOfCleansing.class, PotionOfPurity.class);
		
		regToExo.put(PotionOfParalyticGas.class, PotionOfEarthenArmor.class);
		exoToReg.put(PotionOfEarthenArmor.class, PotionOfParalyticGas.class);

        regToExo.put(PotionOfDarkness.class, PotionOfEternalDarkness.class);
        exoToReg.put(PotionOfEternalDarkness.class, PotionOfDarkness.class);

        regToExo.put(PotionOfDisease.class, PotionOfSuperBug.class);
        exoToReg.put(PotionOfSuperBug.class, PotionOfDisease.class);

        regToExo.put(PotionOfEruption.class, PotionOfEarthquake.class);
        exoToReg.put(PotionOfEarthquake.class, PotionOfEruption.class);

        regToExo.put(PotionOfExplosion.class, PotionOfCataclysm.class);
        exoToReg.put(PotionOfCataclysm.class, PotionOfExplosion.class);

        regToExo.put(PotionOfFirestorm.class, PotionOfHellstorm.class);
        exoToReg.put(PotionOfHellstorm.class, PotionOfFirestorm.class);

        regToExo.put(PotionOfFood.class, PotionOfFasting.class);
        exoToReg.put(PotionOfFasting.class, PotionOfFood.class);

        regToExo.put(PotionOfGlowing.class, PotionOfInnerGlow.class);
        exoToReg.put(PotionOfInnerGlow.class, PotionOfGlowing.class);

        regToExo.put(PotionOfHealth.class, PotionOfSuperHealth.class);
        exoToReg.put(PotionOfSuperHealth.class, PotionOfHealth.class);

        regToExo.put(PotionOfHeat.class, PotionOfInfernalHeat.class);
        exoToReg.put(PotionOfInfernalHeat.class, PotionOfHeat.class);

        regToExo.put(PotionOfIce.class, PotionOfBlizzard.class);
        exoToReg.put(PotionOfBlizzard.class, PotionOfIce.class);

        regToExo.put(PotionOfLight.class, PotionOfRetribution.class);
        exoToReg.put(PotionOfRetribution.class, PotionOfLight.class);

        regToExo.put(PotionOfMuscle.class, PotionOfSuperStrength.class);
        exoToReg.put(PotionOfSuperStrength.class, PotionOfMuscle.class);

        regToExo.put(PotionOfPlants.class, PotionOfBiomass.class);
        exoToReg.put(PotionOfBiomass.class, PotionOfPlants.class);

		regToExo.put(PotionOfPower.class, PotionOfSuperPower.class);
        exoToReg.put(PotionOfSuperPower.class, PotionOfPower.class);

        regToExo.put(PotionOfRain.class, PotionOfRainStorm.class);
        exoToReg.put(PotionOfRainStorm.class, PotionOfRain.class);

        regToExo.put(PotionOfRegrowth.class, PotionOfNature.class);
        exoToReg.put(PotionOfNature.class, PotionOfRegrowth.class);

        regToExo.put(PotionOfSecretion.class, PotionOfNumbness.class);
        exoToReg.put(PotionOfNumbness.class, PotionOfSecretion.class);

        regToExo.put(PotionOfSpirit.class, PotionOfTorment.class);
        exoToReg.put(PotionOfTorment.class, PotionOfSpirit.class);

        regToExo.put(PotionOfSpores.class, PotionOfMutation.class);
        exoToReg.put(PotionOfMutation.class, PotionOfSpores.class);

        regToExo.put(PotionOfSteam.class, PotionOfHotSteam.class);
        exoToReg.put(PotionOfHotSteam.class, PotionOfSteam.class);

        regToExo.put(PotionOfSunlight.class, PotionOfUltraviolet.class);
        exoToReg.put(PotionOfUltraviolet.class, PotionOfSunlight.class);

        regToExo.put(PotionOfTeleportation.class, PotionOfSpace.class);
        exoToReg.put(PotionOfSpace.class, PotionOfTeleportation.class);

        regToExo.put(PotionOfTime.class, PotionOfTimeStop.class);
        exoToReg.put(PotionOfTimeStop.class, PotionOfTime.class);
	}
	
	@Override
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( exoToReg.get(this.getClass()) ));
	}
	
	@Override
	public void setKnown() {
		if (!isKnown()) {
			handler.know(exoToReg.get(this.getClass()));
			updateQuickslot();
			Potion p = Dungeon.hero.belongings.getItem(getClass());
			if (p != null)  p.setAction();
			p = Dungeon.hero.belongings.getItem(exoToReg.get(this.getClass()));
			if (p != null)  p.setAction();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(exoToReg.get(this.getClass()))) {
			image = handler.image(exoToReg.get(this.getClass())) + 16;
			color = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	//20 gold more than its none-exotic equivalent
	public int price() {
		try {
			return (exoToReg.get(getClass()).newInstance().price() + 20) * quantity;
		} catch (Exception e){
			OvergrownPixelDungeon.reportException(e);
			return 0;
		}
	}
	
	public static class PotionToExotic extends Recipe{
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			int s = 0;
			Potion p = null;
			
			for (Item i : ingredients){
				if (i instanceof Plant.Seed){
					s++;
				} else if (regToExo.containsKey(i.getClass())) {
					p = (Potion)i;
				}
			}
			
			return p != null && s == 2;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = null;
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
				if (regToExo.containsKey(i.getClass())) {
					try {
						result = regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						OvergrownPixelDungeon.reportException(e);
					}
				}
			}
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (regToExo.containsKey(i.getClass())) {
					try {
						return regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						OvergrownPixelDungeon.reportException(e);
					}
				}
			}
			return null;
			
		}
	}
}
