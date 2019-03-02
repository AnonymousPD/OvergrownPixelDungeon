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

package com.lovecraftpixel.lovecraftpixeldungeon.items;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.ClothArmor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.LeatherArmor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.MailArmor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.PlateArmor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.ScaleArmor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.Artifact;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.CapeOfThorns;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.CloakOfShadows;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.DriedRose;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.EtherealChains;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.HornOfPlenty;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.LloydsBeacon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.MasterThievesArmband;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.SandalsOfNature;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.TalismanOfForesight;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.UnstableSpellbook;
import com.lovecraftpixel.lovecraftpixeldungeon.items.bags.Bag;
import com.lovecraftpixel.lovecraftpixeldungeon.items.food.Food;
import com.lovecraftpixel.lovecraftpixeldungeon.items.food.MysteryMeat;
import com.lovecraftpixel.lovecraftpixeldungeon.items.food.Pasty;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.Potion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfExperience;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfFrost;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfHaste;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfHealing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfInvisibility;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfLevitation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfMindVision;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfParalyticGas;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfPurity;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfStrength;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfToxicGas;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.Ring;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfAccuracy;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfElements;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfEnergy;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfEvasion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfForce;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfFuror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfHaste;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfMight;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfSharpshooting;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfTenacity;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfWealth;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.Scroll;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTerror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.special.RoyalDecreeOfTheEmperor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.Runestone;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfAffection;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfAggression;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfAugmentation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfBlast;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfBlink;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfClairvoyance;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfDeepenedSleep;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfDetectCurse;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfEnchantment;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfFlock;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfIntuition;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfShock;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.Wand;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfBlastWave;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfCorrosion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfCorruption;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfDisintegration;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfFireblast;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfFrost;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfLightning;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfMagicMissile;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfPrismaticLight;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfRegrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfTransfusion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.BattleAxe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Club;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Crossbow;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Dagger;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Dirk;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Flail;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Gauntlet;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Glaive;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Gloves;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Greataxe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Greatshield;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Greatsword;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.HandAxe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Katana;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.KnifeGlove;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.KnifeOnAStick;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Longsword;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Mace;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.MagesStaff;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Quarterstaff;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Rapier;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.RoundShield;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.RunicBlade;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Sai;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Scimitar;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Scythe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Shortsword;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Spear;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Sword;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.WarHammer;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.Whip;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee.WornShortsword;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.Bolas;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.FishingSpear;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.Javelin;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.Shuriken;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.ThrowingSpear;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.Tomahawk;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.Trident;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.darts.Dart;
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
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 6,    MeleeWeapon.class),
		WEP_T1	( 0,    MeleeWeapon.class),
		WEP_T2	( 0,    MeleeWeapon.class),
		WEP_T3	( 0,    MeleeWeapon.class),
		WEP_T4	( 0,    MeleeWeapon.class),
		WEP_T5	( 0,    MeleeWeapon.class),
		
		ARMOR	( 4,    Armor.class ),
		
		MISSILE ( 3,    MissileWeapon.class ),
		MIS_T1  ( 0,    MissileWeapon.class ),
		MIS_T2  ( 0,    MissileWeapon.class ),
		MIS_T3  ( 0,    MissileWeapon.class ),
		MIS_T4  ( 0,    MissileWeapon.class ),
		MIS_T5  ( 0,    MissileWeapon.class ),
		
		WAND	( 3,    Wand.class ),
		RING	( 1,    Ring.class ),
		ARTIFACT( 1,    Artifact.class),
		
		FOOD	( 0,    Food.class ),
		
		POTION	( 20,   Potion.class ),
		SEED	( 0,    Plant.Seed.class ), //dropped by grass
		
		SCROLL	( 20,   Scroll.class ),
		STONE   ( 2,    Runestone.class),
		
		GOLD	( 18,   Gold.class );
		
		public Class<?>[] classes;
		public float[] probs;
		
		public float prob;
		public Class<? extends Item> superClass;
		
		private Category( float prob, Class<? extends Item> superClass ) {
			this.prob = prob;
			this.superClass = superClass;
		}
		
		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}
			
			return item instanceof Bag ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
		}
		
		private static final float[] INITIAL_ARTIFACT_PROBS = new float[]{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1};
		
		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };
			
			POTION.classes = new Class<?>[]{
					PotionOfStrength.class, //2 drop every chapter, see Dungeon.posNeeded()
					PotionOfHealing.class,
					PotionOfMindVision.class,
					PotionOfFrost.class,
					PotionOfLiquidFlame.class,
					PotionOfToxicGas.class,
					PotionOfHaste.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfParalyticGas.class,
					PotionOfPurity.class,
					PotionOfExperience.class};
			POTION.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			
			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Blindweed.Seed.class,
					Dreamfoil.Seed.class,
					Earthroot.Seed.class,
					Fadeleaf.Seed.class,
					Firebloom.Seed.class,
					Icecap.Seed.class,
					Sorrowmoss.Seed.class,
					Stormvine.Seed.class,
					Sungrass.Seed.class,
					Swiftthistle.Seed.class,
					Starflower.Seed.class,
                    Apricobush.Seed.class,
                    Blackholeflower.Seed.class,
                    Butterlion.Seed.class,
                    Chandaliertail.Seed.class,
                    Chillisnapper.Seed.class,
                    Crimsonpepper.Seed.class,
                    Firefoxglove.Seed.class,
                    Frostcorn.Seed.class,
                    Musclemoss.Seed.class,
                    Nightshadeonion.Seed.class,
                    Parasiteshrub.Seed.class,
                    Snowhedge.Seed.class,
                    Steamweed.Seed.class,
                    Sunbloom.Seed.class,
                    Tomatobush.Seed.class,
                    Waterweed.Seed.class,
                    Willowcane.Seed.class,
                    Witherfennel.Seed.class,
                    Grasslilly.Seed.class,
                    Kiwivetch.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class,
                    Suncarnivore.Seed.class,
                    Venusflytrap.Seed.class};
			SEED.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			
			SCROLL.classes = new Class<?>[]{
					ScrollOfUpgrade.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfIdentify.class,
					ScrollOfRemoveCurse.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfTeleportation.class,
					ScrollOfLullaby.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class,
                    RoyalDecreeOfTheEmperor.class //doesn't naturally spawn
			};
			SCROLL.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1, 0 };
			
			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,   //1 is guaranteed to drop on floors 6-19
					StoneOfAugmentation.class,  //1 is sold in each shop
					StoneOfIntuition.class,     //1 additional stone is also dropped on floors 1-3
					StoneOfAggression.class,
					StoneOfAffection.class,
					StoneOfBlast.class,
					StoneOfBlink.class,
					StoneOfClairvoyance.class,
					StoneOfDeepenedSleep.class,
					StoneOfDetectCurse.class,
					StoneOfFlock.class,
					StoneOfShock.class
			};
			STONE.probs = new float[]{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			
			//TODO: add last ones when implemented
			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfCorrosion.class,
					WandOfBlastWave.class,
					//WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					//WandOfWarding.class,
					WandOfTransfusion.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 5, 4, 4, 4, 4, 3, /*3,*/ 3, 3, /*3,*/ 3, 3, 3 };
			
			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};
			
			WEP_T1.classes = new Class<?>[]{
					WornShortsword.class,
					Gloves.class,
					Dagger.class,
					MagesStaff.class,
                    KnifeGlove.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 1, 0, 1 };
			
			WEP_T2.classes = new Class<?>[]{
					Shortsword.class,
					HandAxe.class,
					Spear.class,
					Quarterstaff.class,
					Dirk.class,
                    KnifeOnAStick.class
			};
			WEP_T2.probs = new float[]{ 6, 5, 5, 4, 4, 3 };
			
			WEP_T3.classes = new Class<?>[]{
					Sword.class,
					Mace.class,
					Scimitar.class,
					RoundShield.class,
					Sai.class,
					Whip.class,
                    Club.class,
                    Rapier.class,
                    Scythe.class
			};
			WEP_T3.probs = new float[]{ 6, 5, 5, 4, 4, 4, 3, 3, 3 };
			
			WEP_T4.classes = new Class<?>[]{
					Longsword.class,
					BattleAxe.class,
					Flail.class,
					RunicBlade.class,
					AssassinsBlade.class,
					Crossbow.class,
                    Katana.class
			};
			WEP_T4.probs = new float[]{ 6, 5, 5, 4, 4, 4, 3 };
			
			WEP_T5.classes = new Class<?>[]{
					Greatsword.class,
					WarHammer.class,
					Glaive.class,
					Greataxe.class,
					Greatshield.class,
					Gauntlet.class
			};
			WEP_T5.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{
					ClothArmor.class,
					LeatherArmor.class,
					MailArmor.class,
					ScaleArmor.class,
					PlateArmor.class };
			ARMOR.probs = new float[]{ 0, 0, 0, 0, 0 };
			
			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};
			
			MIS_T1.classes = new Class<?>[]{
					Dart.class,
					ThrowingKnife.class
			};
			MIS_T1.probs = new float[]{ 1, 1 };
			
			MIS_T2.classes = new Class<?>[]{
					FishingSpear.class,
					Shuriken.class
			};
			MIS_T2.probs = new float[]{ 4, 3 };
			
			MIS_T3.classes = new Class<?>[]{
					ThrowingSpear.class,
					Bolas.class
			};
			MIS_T3.probs = new float[]{ 4, 3 };
			
			MIS_T4.classes = new Class<?>[]{
					Javelin.class,
					Tomahawk.class
			};
			MIS_T4.probs = new float[]{ 4, 3 };
			
			MIS_T5.classes = new Class<?>[]{
					Trident.class,
					ThrowingHammer.class
			};
			MIS_T5.probs = new float[]{ 4, 3 };
			
			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };
			
			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			
			ARTIFACT.classes = new Class<?>[]{
					CapeOfThorns.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					AlchemistsToolkit.class,
					DriedRose.class,
					LloydsBeacon.class,
					EtherealChains.class
			};
			ARTIFACT.probs = INITIAL_ARTIFACT_PROBS.clone();
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 70, 20,  8,  2},
			{0, 25, 50, 20,  5},
			{0, 10, 40, 40, 10},
			{0,  5, 20, 50, 25},
			{0,  2,  8, 20, 70}
	};
	
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();
	
	public static void reset() {
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, cat.prob );
		}
	}
	
	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			reset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}
	
	public static Item random( Category cat ) {
		try {
			
			switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomWeapon();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			default:
				return ((Item)cat.classes[Random.chances( cat.probs )].newInstance()).random();
			}
			
		} catch (Exception e) {

			LovecraftPixelDungeon.reportException(e);
			return null;
			
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		try {
			
			return ((Item)cl.newInstance()).random();
			
		} catch (Exception e) {

			LovecraftPixelDungeon.reportException(e);
			return null;
			
		}
	}

	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 5);
	}
	
	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Armor a = (Armor)Category.ARMOR.classes[Random.chances(floorSetTierProbs[floorSet])].newInstance();
			a.random();
			return a;
		} catch (Exception e) {
			LovecraftPixelDungeon.reportException(e);
			return null;
		}
	}

	public static final Category[] wepTiers = new Category[]{
			Category.WEP_T1,
			Category.WEP_T2,
			Category.WEP_T3,
			Category.WEP_T4,
			Category.WEP_T5
	};

	public static MeleeWeapon randomWeapon(){
		return randomWeapon(Dungeon.depth / 5);
	}
	
	public static MeleeWeapon randomWeapon(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Category c = wepTiers[Random.chances(floorSetTierProbs[floorSet])];
			MeleeWeapon w = (MeleeWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			LovecraftPixelDungeon.reportException(e);
			return null;
		}
	}
	
	public static final Category[] misTiers = new Category[]{
			Category.MIS_T1,
			Category.MIS_T2,
			Category.MIS_T3,
			Category.MIS_T4,
			Category.MIS_T5
	};
	
	public static MissileWeapon randomMissile(){
		return randomMissile(Dungeon.depth / 5);
	}
	
	public static MissileWeapon randomMissile(int floorSet) {
		
		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		
		try {
			Category c = misTiers[Random.chances(floorSetTierProbs[floorSet])];
			MissileWeapon w = (MissileWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			LovecraftPixelDungeon.reportException(e);
			return null;
		}
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		try {
			Category cat = Category.ARTIFACT;
			int i = Random.chances( cat.probs );

			//if no artifacts are left, return null
			if (i == -1){
				return null;
			}
			
			Class<?extends Artifact> art = (Class<? extends Artifact>) cat.classes[i];

			if (removeArtifact(art)) {
				Artifact artifact = art.newInstance();
				
				artifact.random();
				
				return artifact;
			} else {
				return null;
			}

		} catch (Exception e) {
			LovecraftPixelDungeon.reportException(e);
			return null;
		}
	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		if (spawnedArtifacts.contains(artifact))
			return false;

		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++)
			if (cat.classes[i].equals(artifact)) {
				if (cat.probs[i] == 1){
					cat.probs[i] = 0;
					spawnedArtifacts.add(artifact);
					return true;
				} else
					return false;
			}

		return false;
	}

	//resets artifact probabilities, for new dungeons
	public static void initArtifacts() {
		Category.ARTIFACT.probs = Category.INITIAL_ARTIFACT_PROBS.clone();
		spawnedArtifacts = new ArrayList<>();
	}

	private static ArrayList<Class<?extends Artifact>> spawnedArtifacts = new ArrayList<>();
	
	private static final String GENERAL_PROBS = "general_probs";
	private static final String SPAWNED_ARTIFACTS = "spawned_artifacts";
	
	public static void storeInBundle(Bundle bundle) {
		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);
		
		bundle.put( SPAWNED_ARTIFACTS, spawnedArtifacts.toArray(new Class[0]));
	}

	public static void restoreFromBundle(Bundle bundle) {
		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		} else {
			reset();
		}
		
		initArtifacts();
		if (bundle.contains(SPAWNED_ARTIFACTS)){
			for ( Class<?extends Artifact> artifact : bundle.getClassArray(SPAWNED_ARTIFACTS) ){
				removeArtifact(artifact);
			}
		//pre-0.6.1 saves
		} else if (bundle.contains("artifacts")) {
			String[] names = bundle.getStringArray("artifacts");
			Category cat = Category.ARTIFACT;

			for (String artifact : names)
				for (int i = 0; i < cat.classes.length; i++)
					if (cat.classes[i].getSimpleName().equals(artifact))
						cat.probs[i] = 0;
		}
	}
}
