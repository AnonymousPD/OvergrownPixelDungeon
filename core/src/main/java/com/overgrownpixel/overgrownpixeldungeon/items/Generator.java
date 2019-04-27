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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ClothArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.LeatherArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MailArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.PlateArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ScaleArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.Artifact;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.CapeOfThorns;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.CloakOfShadows;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.DriedRose;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.EtherealChains;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.HornOfPlenty;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.LloydsBeacon;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.MasterThievesArmband;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TalismanOfForesight;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.UnstableSpellbook;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.Bag;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Food;
import com.overgrownpixel.overgrownpixeldungeon.items.food.MysteryMeat;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Pasty;
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
import com.overgrownpixel.overgrownpixeldungeon.items.rings.Ring;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfAccuracy;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfElements;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfEnergy;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfEvasion;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfForce;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfFuror;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfHaste;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfMight;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfSharpshooting;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfTenacity;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfWealth;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.Scroll;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRage;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTerror;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfUpgrade;
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
import com.overgrownpixel.overgrownpixeldungeon.items.wands.Wand;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorrosion;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorruption;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfDisintegration;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfFireblast;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfFrost;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfLightning;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfMagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfPrismaticLight;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfTransfusion;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.BattleAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Club;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Crossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Dagger;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Dirk;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Flail;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Gauntlet;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Glaive;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Gloves;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Greataxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Greatshield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Greatsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.HandAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Katana;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.KnifeGlove;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.KnifeOnAStick;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Longsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Mace;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MagesStaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Quarterstaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Rapier;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.RoundShield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.RunicBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Sai;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Scimitar;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Scythe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Shortsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Spear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Sword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.WarHammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Whip;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.WornShortsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Bolas;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.FishingSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Javelin;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Shuriken;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.ThrowingSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Tomahawk;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Trident;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.AdrenalineDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.BlindingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ChaosDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ChillingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ConfusingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.CorruptionDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.Dart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.DiseaseDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.DisplacingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.EarthquakeDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.FirefoxDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.FreezingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.HealingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.HealthDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.HeatDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.HolyDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.IncendiaryDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ParalyticDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ParasiticDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.PeanutMarkDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.PoisonDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.PushingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.RootingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.RotDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.ShockingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.SleepDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.SlownessDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.SmokingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.SpicyDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.StormDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.SunDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TeleportingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TomatoDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TrackingDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.WaterDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.WitherDart;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.WraithDart;
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
		
		POTION	    ( 20,   Potion.class ),
        ALLPOTIONS	( 20,   Potion.class ),
		SEED	    ( 0,    Plant.Seed.class ),
        BASESEED	( 0,    Plant.Seed.class ),
        SEEDWATER	( 0,    Plant.Seed.class ),
        SEEDSEWER	( 0,    Plant.Seed.class ),
        SEEDPRISON	( 0,    Plant.Seed.class ),
        SEEDCAVES	( 0,    Plant.Seed.class ),
        SEEDCITY	( 0,    Plant.Seed.class ),
        SEEDHELL	( 0,    Plant.Seed.class ),

        DARTS	    ( 0,    TippedDart.class ),
		
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
		
		private static final float[] INITIAL_ARTIFACT_PROBS = new float[]{ 0, 1, 0, 1, 1, /*1,*/ 1, 1, 1, 1, 1, 0, 1};
		
		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };

            DARTS.classes = new Class<?>[]{
                    AdrenalineDart.class,
                    BlindingDart.class,
                    SleepDart.class,
                    ParalyticDart.class,
                    DisplacingDart.class,
                    IncendiaryDart.class,
                    ChillingDart.class,
                    RotDart.class,
                    PoisonDart.class,
                    HolyDart.class,
                    ShockingDart.class,
                    HealingDart.class,
                    HealthDart.class,
                    TeleportingDart.class,
                    EarthquakeDart.class,
                    TrackingDart.class,
                    HeatDart.class,
                    SpicyDart.class,
                    FirefoxDart.class,
                    FreezingDart.class,
                    ChaosDart.class,
                    RootingDart.class,
                    PushingDart.class,
                    SmokingDart.class,
                    ParasiticDart.class,
                    PeanutMarkDart.class,
                    WraithDart.class,
                    DiseaseDart.class,
                    StormDart.class,
                    SunDart.class,
                    CorruptionDart.class,
                    TomatoDart.class,
                    ConfusingDart.class,
                    WaterDart.class,
                    SlownessDart.class,
                    WitherDart.class
            };
            DARTS.probs = new float[]{
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1,
            };
			
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

            ALLPOTIONS.classes = new Class<?>[]{
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
                    PotionOfTime.class};
            ALLPOTIONS.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
			
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
			SEED.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

            BASESEED.classes = new Class<?>[]{
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
                    Starflower.Seed.class};
            BASESEED.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1 };

            SEEDWATER.classes = new Class<?>[]{
                    Steamweed.Seed.class,
                    Waterweed.Seed.class,
                    };
            SEEDWATER.probs = new float[]{ 10, 10 };

            SEEDSEWER.classes = new Class<?>[]{
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
                    Frostcorn.Seed.class,
                    Musclemoss.Seed.class,
                    Sunbloom.Seed.class,
                    Tomatobush.Seed.class,
                    Willowcane.Seed.class,
                    Grasslilly.Seed.class,
                    Kiwivetch.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class};
            SEEDSEWER.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

            SEEDPRISON.classes = new Class<?>[]{
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
                    Sunbloom.Seed.class,
                    Tomatobush.Seed.class,
                    Willowcane.Seed.class,
                    Grasslilly.Seed.class,
                    Kiwivetch.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class,
                    Venusflytrap.Seed.class};
            SEEDPRISON.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

            SEEDCAVES.classes = new Class<?>[]{
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
                    Tomatobush.Seed.class,
                    Willowcane.Seed.class,
                    Witherfennel.Seed.class,
                    Grasslilly.Seed.class,
                    Kiwivetch.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class,
                    Venusflytrap.Seed.class};
            SEEDCAVES.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

            SEEDCITY.classes = new Class<?>[]{
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
                    Sunbloom.Seed.class,
                    Tomatobush.Seed.class,
                    Willowcane.Seed.class,
                    Witherfennel.Seed.class,
                    Grasslilly.Seed.class,
                    Kiwivetch.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class,
                    Suncarnivore.Seed.class,
                    Venusflytrap.Seed.class};
            SEEDCITY.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

            SEEDHELL.classes = new Class<?>[]{
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
                    Sunbloom.Seed.class,
                    Tomatobush.Seed.class,
                    Willowcane.Seed.class,
                    Witherfennel.Seed.class,
                    Peanutpetal.Seed.class,
                    Rose.Seed.class,                                                                                                    //
                    Suncarnivore.Seed.class,                                                                                               //
                    Venusflytrap.Seed.class};                                                                                                  //
            SEEDHELL.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			
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
                    //RoyalDecreeOfTheEmperor.class //doesn't naturally spawn
			};
			SCROLL.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1, /*0*/ };
			
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
					//SandalsOfNature.class,
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
		Category cat;
		do {
            reset();
            cat = Random.chances( categoryProbs );
        }while (cat == null || cat == Category.ALLPOTIONS
                || cat == Category.SEEDCAVES
                || cat == Category.SEEDCITY
                || cat == Category.SEEDHELL
                || cat == Category.SEEDPRISON
                || cat == Category.SEEDSEWER
                || cat == Category.SEEDWATER
                || cat == Category.DARTS
                || cat == Category.BASESEED);
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

			OvergrownPixelDungeon.reportException(e);
			return null;
			
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		try {
			
			return ((Item)cl.newInstance()).random();
			
		} catch (Exception e) {

			OvergrownPixelDungeon.reportException(e);
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
			OvergrownPixelDungeon.reportException(e);
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
			OvergrownPixelDungeon.reportException(e);
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
			OvergrownPixelDungeon.reportException(e);
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
			OvergrownPixelDungeon.reportException(e);
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
