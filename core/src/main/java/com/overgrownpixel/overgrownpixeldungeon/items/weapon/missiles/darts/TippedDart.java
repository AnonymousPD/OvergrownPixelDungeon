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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.PinCushion;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
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

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TippedDart extends Dart {
	
	{
		tier = 2;
		
		//so that slightly more than 1.5x durability is needed for 2 uses
		baseUses = 0.65f;
	}
	
	//exact same damage as regular darts, despite being higher tier.
	
	@Override
	protected void rangedHit(Char enemy, int cell) {
		super.rangedHit( enemy, cell);
		
		//need to spawn a dart
		if (durability <= 0){
			//attempt to stick the dart to the enemy, just drop it if we can't.
			Dart d = new Dart();
			if (enemy.isAlive() && sticky) {
				PinCushion p = Buff.affect(enemy, PinCushion.class);
				if (p.target == enemy){
					p.stick(d);
					return;
				}
			}
			Dungeon.level.drop( d, enemy.pos ).sprite.drop();
		}
	}
	
	@Override
	protected float durabilityPerUse() {
		float use = super.durabilityPerUse();
		
		if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
			use /= 2f;
		}
		
		return use;
	}
	
	private static HashMap<Class<?extends Plant.Seed>, Class<?extends TippedDart>> types = new HashMap<>();
	static {
		types.put(Blindweed.Seed.class,     BlindingDart.class);
		types.put(Dreamfoil.Seed.class,     SleepDart.class);
		types.put(Earthroot.Seed.class,     ParalyticDart.class);
		types.put(Fadeleaf.Seed.class,      DisplacingDart.class);
		types.put(Firebloom.Seed.class,     IncendiaryDart.class);
		types.put(Icecap.Seed.class,        ChillingDart.class);
		types.put(Rotberry.Seed.class,      RotDart.class);
		types.put(Sorrowmoss.Seed.class,    PoisonDart.class);
		types.put(Starflower.Seed.class,    HolyDart.class);
		types.put(Stormvine.Seed.class,     ShockingDart.class);
		types.put(Sungrass.Seed.class,      HealingDart.class);
		types.put(Swiftthistle.Seed.class,  AdrenalineDart.class);

		types.put(Apricobush.Seed.class,            HealthDart.class);
        types.put(Blackholeflower.Seed.class,       TeleportingDart.class);
        types.put(Butterlion.Seed.class,            EarthquakeDart.class);
        types.put(Chandaliertail.Seed.class,        TrackingDart.class);
        types.put(Chillisnapper.Seed.class,         HeatDart.class);
        types.put(Crimsonpepper.Seed.class,         SpicyDart.class);
        types.put(Firefoxglove.Seed.class,          FirefoxDart.class);
        types.put(Frostcorn.Seed.class,             FreezingDart.class);
        types.put(Grasslilly.Seed.class,            ChaosDart.class);
        types.put(Kiwivetch.Seed.class,             RootingDart.class);
        types.put(Musclemoss.Seed.class,            PushingDart.class);
        types.put(Nightshadeonion.Seed.class,       SmokingDart.class);
        types.put(Parasiteshrub.Seed.class,         ParasiticDart.class);
        types.put(Peanutpetal.Seed.class,           PeanutMarkDart.class);
        types.put(Rose.Seed.class,                  WraithDart.class);
        types.put(Snowhedge.Seed.class,             DiseaseDart.class);
        types.put(Steamweed.Seed.class,             StormDart.class);
        types.put(Sunbloom.Seed.class,              SunDart.class);
        types.put(Suncarnivore.Seed.class,          CorruptionDart.class);
        types.put(Tomatobush.Seed.class,            TomatoDart.class);
        types.put(Venusflytrap.Seed.class,          ConfusingDart.class);
        types.put(Waterweed.Seed.class,             WaterDart.class);
        types.put(Willowcane.Seed.class,            SlownessDart.class);
        types.put(Witherfennel.Seed.class,          WitherDart.class);
        types.put(WandOfRegrowth.Seedpod.Seed.class,SeedChaosDart.class);
        types.put(WandOfRegrowth.Dewcatcher.Seed.class,DewDart.class);
	}
	
	public static TippedDart randomTipped(){
		Plant.Seed s;
		do{
			s = (Plant.Seed) Generator.random(Generator.Category.SEED);
		} while (!types.containsKey(s.getClass()));
		
		try{
			return (TippedDart) types.get(s.getClass()).newInstance().quantity(2);
		} catch (Exception e) {
			OvergrownPixelDungeon.reportException(e);
			return null;
		}
		
	}
	
	public static class TipDart extends Recipe{
		
		@Override
		//also sorts ingredients if it can
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;
			
			if (ingredients.get(0).getClass() == Dart.class){
				if (!(ingredients.get(1) instanceof Plant.Seed)){
					return false;
				}
			} else if (ingredients.get(0) instanceof Plant.Seed){
				if (ingredients.get(1).getClass() == Dart.class){
					Item temp = ingredients.get(0);
					ingredients.set(0, ingredients.get(1));
					ingredients.set(1, temp);
				} else {
					return false;
				}
			} else {
				return false;
			}
			
			Plant.Seed seed = (Plant.Seed) ingredients.get(1);
			
			if (ingredients.get(0).quantity() >= 1
					&& seed.quantity() >= 1
					&& types.containsKey(seed.getClass())){
				return true;
			}
			
			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			int produced = Math.min(2, ingredients.get(0).quantity());
			
			ingredients.get(0).quantity(ingredients.get(0).quantity() - produced);
			ingredients.get(1).quantity(ingredients.get(1).quantity() - 1);
			
			try{
				return types.get(ingredients.get(1).getClass()).newInstance().quantity(produced);
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
			
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			try{
				int produced = Math.min(2, ingredients.get(0).quantity());
				return types.get(ingredients.get(1).getClass()).newInstance().quantity( produced );
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
	}
}
