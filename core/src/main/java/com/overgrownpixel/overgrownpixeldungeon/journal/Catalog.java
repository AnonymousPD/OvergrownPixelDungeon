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

package com.overgrownpixel.overgrownpixeldungeon.journal;

import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ClothArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.HuntressArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.LeatherArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MageArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MailArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.PlateArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.RogueArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ScaleArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.WarriorArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.CloakOfShadows;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.DriedRose;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.EtherealChains;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.HornOfPlenty;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.MasterThievesArmband;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.SandalsOfNature;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TalismanOfForesight;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.UnstableSpellbook;
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
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.special.RoyalDecreeOfTheEmperor;
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
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public enum Catalog {
	
	WEAPONS,
	ARMOR,
	WANDS,
	RINGS,
	ARTIFACTS,
	POTIONS,
	SCROLLS;
	
	private LinkedHashMap<Class<? extends Item>, Boolean> seen = new LinkedHashMap<>();
	
	public Collection<Class<? extends Item>> items(){
		return seen.keySet();
	}
	
	public boolean allSeen(){
		for (Class<?extends Item> item : items()){
			if (!seen.get(item)){
				return false;
			}
		}
		return true;
	}
	
	static {
		WEAPONS.seen.put( WornShortsword.class,             false);
		WEAPONS.seen.put( Gloves.class,                     false);
		WEAPONS.seen.put( Dagger.class,                     false);
		WEAPONS.seen.put( MagesStaff.class,                 false);
		//WEAPONS.seen.put( Boomerang.class,                  false);
		WEAPONS.seen.put( Shortsword.class,                 false);
		WEAPONS.seen.put( HandAxe.class,                    false);
		WEAPONS.seen.put( Spear.class,                      false);
		WEAPONS.seen.put( Quarterstaff.class,               false);
		WEAPONS.seen.put( Dirk.class,                       false);
		WEAPONS.seen.put( Sword.class,                      false);
		WEAPONS.seen.put( Mace.class,                       false);
		WEAPONS.seen.put( Scimitar.class,                   false);
		WEAPONS.seen.put( RoundShield.class,                false);
		WEAPONS.seen.put( Sai.class,                        false);
		WEAPONS.seen.put( Whip.class,                       false);
		WEAPONS.seen.put( Longsword.class,                  false);
		WEAPONS.seen.put( BattleAxe.class,                  false);
		WEAPONS.seen.put( Flail.class,                      false);
		WEAPONS.seen.put( RunicBlade.class,                 false);
		WEAPONS.seen.put( AssassinsBlade.class,             false);
		WEAPONS.seen.put( Crossbow.class,                   false);
		WEAPONS.seen.put( Greatsword.class,                 false);
		WEAPONS.seen.put( WarHammer.class,                  false);
		WEAPONS.seen.put( Glaive.class,                     false);
		WEAPONS.seen.put( Greataxe.class,                   false);
		WEAPONS.seen.put( Greatshield.class,                false);
		WEAPONS.seen.put( Gauntlet.class,                   false);
        WEAPONS.seen.put( Club.class,                       false);
        WEAPONS.seen.put( Katana.class,                     false);
        WEAPONS.seen.put( KnifeGlove.class,                 false);
        WEAPONS.seen.put( KnifeOnAStick.class,              false);
        WEAPONS.seen.put( Rapier.class,                     false);
        WEAPONS.seen.put( Scythe.class,                     false);
	
		ARMOR.seen.put( ClothArmor.class,                   false);
		ARMOR.seen.put( LeatherArmor.class,                 false);
		ARMOR.seen.put( MailArmor.class,                    false);
		ARMOR.seen.put( ScaleArmor.class,                   false);
		ARMOR.seen.put( PlateArmor.class,                   false);
		ARMOR.seen.put( WarriorArmor.class,                 false);
		ARMOR.seen.put( MageArmor.class,                    false);
		ARMOR.seen.put( RogueArmor.class,                   false);
		ARMOR.seen.put( HuntressArmor.class,                false);
	
		WANDS.seen.put( WandOfMagicMissile.class,           false);
		WANDS.seen.put( WandOfLightning.class,              false);
		WANDS.seen.put( WandOfDisintegration.class,         false);
		WANDS.seen.put( WandOfFireblast.class,              false);
		WANDS.seen.put( WandOfCorrosion.class,              false);
		WANDS.seen.put( WandOfBlastWave.class,              false);
		//WANDS.seen.put( WandOfLivingEarth.class,          false);
		WANDS.seen.put( WandOfFrost.class,                  false);
		WANDS.seen.put( WandOfPrismaticLight.class,         false);
		//WANDS.seen.put( WandOfWarding.class,              false);
		WANDS.seen.put( WandOfTransfusion.class,            false);
		WANDS.seen.put( WandOfCorruption.class,             false);
		WANDS.seen.put( WandOfRegrowth.class,               false);
	
		RINGS.seen.put( RingOfAccuracy.class,               false);
		RINGS.seen.put( RingOfEnergy.class,                 false);
		RINGS.seen.put( RingOfElements.class,               false);
		RINGS.seen.put( RingOfEvasion.class,                false);
		RINGS.seen.put( RingOfForce.class,                  false);
		RINGS.seen.put( RingOfFuror.class,                  false);
		RINGS.seen.put( RingOfHaste.class,                  false);
		RINGS.seen.put( RingOfMight.class,                  false);
		RINGS.seen.put( RingOfSharpshooting.class,          false);
		RINGS.seen.put( RingOfTenacity.class,               false);
		RINGS.seen.put( RingOfWealth.class,                 false);
	
		ARTIFACTS.seen.put( AlchemistsToolkit.class,        false);
		//ARTIFACTS.seen.put( CapeOfThorns.class,             false);
		ARTIFACTS.seen.put( ChaliceOfBlood.class,           false);
		ARTIFACTS.seen.put( CloakOfShadows.class,           false);
		ARTIFACTS.seen.put( DriedRose.class,                false);
		ARTIFACTS.seen.put( EtherealChains.class,           false);
		ARTIFACTS.seen.put( HornOfPlenty.class,             false);
		//ARTIFACTS.seen.put( LloydsBeacon.class,             false);
		ARTIFACTS.seen.put( MasterThievesArmband.class,     false);
		ARTIFACTS.seen.put( SandalsOfNature.class,          false);
		ARTIFACTS.seen.put( TalismanOfForesight.class,      false);
		ARTIFACTS.seen.put( TimekeepersHourglass.class,     false);
		ARTIFACTS.seen.put( UnstableSpellbook.class,        false);
	
		POTIONS.seen.put( PotionOfHealing.class,            false);
		POTIONS.seen.put( PotionOfStrength.class,           false);
		POTIONS.seen.put( PotionOfLiquidFlame.class,        false);
		POTIONS.seen.put( PotionOfFrost.class,              false);
		POTIONS.seen.put( PotionOfToxicGas.class,           false);
		POTIONS.seen.put( PotionOfParalyticGas.class,       false);
		POTIONS.seen.put( PotionOfPurity.class,             false);
		POTIONS.seen.put( PotionOfLevitation.class,         false);
		POTIONS.seen.put( PotionOfMindVision.class,         false);
		POTIONS.seen.put( PotionOfInvisibility.class,       false);
		POTIONS.seen.put( PotionOfExperience.class,         false);
		POTIONS.seen.put( PotionOfHaste.class,              false);
        POTIONS.seen.put( PotionOfDisease.class,            false);
        POTIONS.seen.put( PotionOfFood.class,               false);
        POTIONS.seen.put( PotionOfLight.class,              false);
        POTIONS.seen.put( PotionOfPlants.class,             false);
        POTIONS.seen.put( PotionOfRegrowth.class,           false);
        POTIONS.seen.put( PotionOfMuscle.class,             false);
        POTIONS.seen.put( PotionOfFirestorm.class,          false);
        POTIONS.seen.put( PotionOfGlowing.class,            false);
        POTIONS.seen.put( PotionOfEruption.class,           false);
        POTIONS.seen.put( PotionOfTeleportation.class,      false);
        POTIONS.seen.put( PotionOfHealth.class,             false);
        POTIONS.seen.put( PotionOfIce.class,                false);
        POTIONS.seen.put( PotionOfHeat.class,               false);
        POTIONS.seen.put( PotionOfDarkness.class,           false);
        POTIONS.seen.put( PotionOfSpores.class,             false);
        POTIONS.seen.put( PotionOfSpirit.class,             false);
        POTIONS.seen.put( PotionOfSteam.class,              false);
        POTIONS.seen.put( PotionOfSunlight.class,           false);
        POTIONS.seen.put( PotionOfExplosion.class,          false);
        POTIONS.seen.put( PotionOfSecretion.class,          false);
        POTIONS.seen.put( PotionOfRain.class,               false);
        POTIONS.seen.put( PotionOfTime.class,               false);
        POTIONS.seen.put( PotionOfPower.class,              false);
	
		SCROLLS.seen.put( ScrollOfIdentify.class,           false);
		SCROLLS.seen.put( ScrollOfUpgrade.class,            false);
		SCROLLS.seen.put( ScrollOfRemoveCurse.class,        false);
		SCROLLS.seen.put( ScrollOfMagicMapping.class,       false);
		SCROLLS.seen.put( ScrollOfTeleportation.class,      false);
		SCROLLS.seen.put( ScrollOfRecharging.class,         false);
		SCROLLS.seen.put( ScrollOfMirrorImage.class,        false);
		SCROLLS.seen.put( ScrollOfTerror.class,             false);
		SCROLLS.seen.put( ScrollOfLullaby.class,            false);
		SCROLLS.seen.put( ScrollOfRage.class,               false);
		SCROLLS.seen.put( ScrollOfRetribution.class,        false);
		SCROLLS.seen.put( ScrollOfTransmutation.class,      false);
        SCROLLS.seen.put( RoyalDecreeOfTheEmperor.class,    false);
	}
	
	public static LinkedHashMap<Catalog, Badges.Badge> catalogBadges = new LinkedHashMap<>();
	static {
		catalogBadges.put(WEAPONS, Badges.Badge.ALL_WEAPONS_IDENTIFIED);
		catalogBadges.put(ARMOR, Badges.Badge.ALL_ARMOR_IDENTIFIED);
		catalogBadges.put(WANDS, Badges.Badge.ALL_WANDS_IDENTIFIED);
		catalogBadges.put(RINGS, Badges.Badge.ALL_RINGS_IDENTIFIED);
		catalogBadges.put(ARTIFACTS, Badges.Badge.ALL_ARTIFACTS_IDENTIFIED);
		catalogBadges.put(POTIONS, Badges.Badge.ALL_POTIONS_IDENTIFIED);
		catalogBadges.put(SCROLLS, Badges.Badge.ALL_SCROLLS_IDENTIFIED);
	}
	
	public static boolean isSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass)) {
				return cat.seen.get(itemClass);
			}
		}
		return false;
	}
	
	public static void setSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass) && !cat.seen.get(itemClass)) {
				cat.seen.put(itemClass, true);
				Journal.saveNeeded = true;
			}
		}
		Badges.validateItemsIdentified();
	}
	
	private static final String CATALOGS = "catalogs";
	
	public static void store( Bundle bundle ){
		
		Badges.loadGlobal();
		
		ArrayList<String> seen = new ArrayList<>();
		
		//if we have identified all items of a set, we use the badge to keep track instead.
		if (!Badges.isUnlocked(Badges.Badge.ALL_ITEMS_IDENTIFIED)) {
			for (Catalog cat : values()) {
				if (!Badges.isUnlocked(catalogBadges.get(cat))) {
					for (Class<? extends Item> item : cat.items()) {
						if (cat.seen.get(item)) seen.add(item.getSimpleName());
					}
				}
			}
		}
		
		bundle.put( CATALOGS, seen.toArray(new String[0]) );
		
	}
	
	public static void restore( Bundle bundle ){
		
		Badges.loadGlobal();
		
		//logic for if we have all badges
		if (Badges.isUnlocked(Badges.Badge.ALL_ITEMS_IDENTIFIED)){
			for ( Catalog cat : values()){
				for (Class<? extends Item> item : cat.items()){
					cat.seen.put(item, true);
				}
			}
			return;
		}
		
		//catalog-specific badge logic
		for (Catalog cat : values()){
			if (Badges.isUnlocked(catalogBadges.get(cat))){
				for (Class<? extends Item> item : cat.items()){
					cat.seen.put(item, true);
				}
			}
		}
		
		//general save/load
		if (bundle.contains(CATALOGS)) {
			List<String> seen = Arrays.asList(bundle.getStringArray(CATALOGS));
			
			//pre-0.6.3 saves
			//TODO should adjust this to tie into the bundling system's class array
			if (seen.contains("WandOfVenom")){
				WANDS.seen.put(WandOfCorrosion.class, true);
			}
			
			for (Catalog cat : values()) {
				for (Class<? extends Item> item : cat.items()) {
					if (seen.contains(item.getSimpleName())) {
						cat.seen.put(item, true);
					}
				}
			}
		}
	}
	
}
