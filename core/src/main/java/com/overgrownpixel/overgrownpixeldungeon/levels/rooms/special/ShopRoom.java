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

package com.overgrownpixel.overgrownpixeldungeon.levels.rooms.special;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Belongings;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.overgrownpixel.overgrownpixeldungeon.items.Ankh;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Heap;
import com.overgrownpixel.overgrownpixeldungeon.items.Honeypot;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.MerchantsBeacon;
import com.overgrownpixel.overgrownpixeldungeon.items.Stylus;
import com.overgrownpixel.overgrownpixeldungeon.items.Torch;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.LeatherArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MailArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.PlateArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ScaleArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.Bag;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.MagicalHolster;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.PotionBandolier;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.ScrollHolder;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.VelvetPouch;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb;
import com.overgrownpixel.overgrownpixeldungeon.items.food.SmallRation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.Scroll;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.Runestone;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfAugmentation;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.Wand;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.BattleAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Greatsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.HandAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Longsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Mace;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Shortsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Sword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.WarHammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Bolas;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.FishingSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Javelin;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Shuriken;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.ThrowingSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Tomahawk;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Trident;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShopRoom extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;
	
	@Override
	public int minWidth() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	@Override
	public int minHeight() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );

		placeShopkeeper( level );

		placeItems( level );
		
		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

	}

	protected void placeShopkeeper( Level level ) {

		int pos = level.pointToCell(center());

		Mob shopkeeper = new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

	}

	protected void placeItems( Level level ){

		if (itemsToSpawn == null)
			itemsToSpawn = generateItems();

		Point itemPlacement = new Point(entrance());
		if (itemPlacement.y == top){
			itemPlacement.y++;
		} else if (itemPlacement.y == bottom) {
			itemPlacement.y--;
		} else if (itemPlacement.x == left){
			itemPlacement.x++;
		} else {
			itemPlacement.x--;
		}

		for (Item item : itemsToSpawn) {

			if (itemPlacement.x == left+1 && itemPlacement.y != top+1){
				itemPlacement.y--;
			} else if (itemPlacement.y == top+1 && itemPlacement.x != right-1){
				itemPlacement.x++;
			} else if (itemPlacement.x == right-1 && itemPlacement.y != bottom-1){
				itemPlacement.y++;
			} else {
				itemPlacement.x--;
			}

			int cell = level.pointToCell(itemPlacement);

			if (level.heaps.get( cell ) != null) {
				do {
					cell = level.pointToCell(random());
				} while (level.heaps.get( cell ) != null || level.findMob( cell ) != null);
			}

			level.drop( item, cell ).type = Heap.Type.FOR_SALE;
		}

	}
	
	protected static ArrayList<Item> generateItems() {

		ArrayList<Item> itemsToSpawn = new ArrayList<>();
		
		switch (Dungeon.depth) {
		case 6:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ? new Shortsword().identify() : new HandAxe()).identify() );
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new FishingSpear().quantity(2) :
					new Shuriken().quantity(2));
			itemsToSpawn.add( new LeatherArmor().identify() );
			break;
			
		case 11:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ? new Sword().identify() : new Mace()).identify() );
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new ThrowingSpear().quantity(2) :
					new Bolas().quantity(2));
			itemsToSpawn.add( new MailArmor().identify() );
			break;
			
		case 16:
			itemsToSpawn.add( (Random.Int( 2 ) == 0 ? new Longsword().identify() : new BattleAxe()).identify() );
			itemsToSpawn.add( Random.Int( 2 ) == 0 ?
					new Javelin().quantity(2) :
					new Tomahawk().quantity(2));
			itemsToSpawn.add( new ScaleArmor().identify() );
			break;
			
		case 21:
			itemsToSpawn.add( Random.Int( 2 ) == 0 ? new Greatsword().identify() : new WarHammer().identify() );
			itemsToSpawn.add( Random.Int(2) == 0 ?
					new Trident().quantity(2) :
					new ThrowingHammer().quantity(2));
			itemsToSpawn.add( new PlateArmor().identify() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
			itemsToSpawn.add( new Torch() );
			break;
		}
		
		itemsToSpawn.add( TippedDart.randomTipped() );

		itemsToSpawn.add( new MerchantsBeacon() );


		itemsToSpawn.add(ChooseBag(Dungeon.hero.belongings));


		itemsToSpawn.add( new PotionOfHealing() );
		for (int i=0; i < 3; i++)
			itemsToSpawn.add( Generator.random( Generator.Category.POTION ) );

		itemsToSpawn.add( new ScrollOfIdentify() );
		itemsToSpawn.add( new ScrollOfRemoveCurse() );
		itemsToSpawn.add( new ScrollOfMagicMapping() );
		itemsToSpawn.add( Generator.random( Generator.Category.SCROLL ) );

		for (int i=0; i < 2; i++)
			itemsToSpawn.add( Random.Int(2) == 0 ?
					Generator.random( Generator.Category.POTION ) :
					Generator.random( Generator.Category.SCROLL ) );


		itemsToSpawn.add( new SmallRation() );
		itemsToSpawn.add( new SmallRation() );
		
		switch (Random.Int(4)){
			case 0:
				itemsToSpawn.add( new Bomb() );
				break;
			case 1:
			case 2:
				itemsToSpawn.add( new Bomb.DoubleBomb() );
				break;
			case 3:
				itemsToSpawn.add( new Honeypot() );
				break;
		}

		itemsToSpawn.add( new Ankh() );
		itemsToSpawn.add( new StoneOfAugmentation() );

		TimekeepersHourglass hourglass = Dungeon.hero.belongings.getItem(TimekeepersHourglass.class);
		if (hourglass != null){
			int bags = 0;
			//creates the given float percent of the remaining bags to be dropped.
			//this way players who get the hourglass late can still max it, usually.
			switch (Dungeon.depth) {
				case 6:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.20f ); break;
				case 11:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.25f ); break;
				case 16:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.50f ); break;
				case 21:
					bags = (int)Math.ceil(( 5-hourglass.sandBags) * 0.80f ); break;
			}

			for(int i = 1; i <= bags; i++){
				itemsToSpawn.add( new TimekeepersHourglass.sandBag());
				hourglass.sandBags ++;
			}
		}

		Item rare;
		switch (Random.Int(10)){
			case 0:
				rare = Generator.random( Generator.Category.WAND );
				rare.level( 0 );
				break;
			case 1:
				rare = Generator.random(Generator.Category.RING);
				rare.level( 0 );
				break;
			case 2:
				rare = Generator.random( Generator.Category.ARTIFACT );
				break;
			default:
				rare = new Stylus();
		}
		rare.cursed = false;
		rare.cursedKnown = true;
		itemsToSpawn.add( rare );

		//hard limit is 63 items + 1 shopkeeper, as shops can't be bigger than 8x8=64 internally
		if (itemsToSpawn.size() > 63)
			throw new RuntimeException("Shop attempted to carry more than 63 items!");

		Random.shuffle(itemsToSpawn);
		return itemsToSpawn;
	}

	protected static Bag ChooseBag(Belongings pack){
	
		//0=pouch, 1=holder, 2=bandolier, 3=holster
		int[] bagItems = new int[4];

		//count up items in the main bag
		for (Item item : pack.backpack.items) {
			if (item instanceof Plant.Seed || item instanceof Runestone)    bagItems[0]++;
			if (item instanceof Scroll)                                     bagItems[1]++;
			if (item instanceof Potion)                                     bagItems[2]++;
			if (item instanceof Wand || item instanceof MissileWeapon)      bagItems[3]++;
		}
		
		//disqualify bags that have already been dropped
		if (Dungeon.LimitedDrops.VELVET_POUCH.dropped())                    bagItems[0] = -1;
		if (Dungeon.LimitedDrops.SCROLL_HOLDER.dropped())                   bagItems[1] = -1;
		if (Dungeon.LimitedDrops.POTION_BANDOLIER.dropped())                bagItems[2] = -1;
		if (Dungeon.LimitedDrops.MAGICAL_HOLSTER.dropped())                 bagItems[3] = -1;
		
		//find the best bag to drop. This does give a preference to later bags, if counts are equal
		int bestBagIdx = 0;
		for (int i = 1; i <= 3; i++){
			if (bagItems[bestBagIdx] <= bagItems[i]){
				bestBagIdx = i;
			}
		}
		
		//drop it, or return nothing if no bag works
		if (bagItems[bestBagIdx] == -1) return null;
		switch (bestBagIdx){
			case 0: default:
				Dungeon.LimitedDrops.VELVET_POUCH.drop();
				return new VelvetPouch();
			case 1:
				Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
				return new ScrollHolder();
			case 2:
				Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
				return new PotionBandolier();
			case 3:
				Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();
				return new MagicalHolster();
		}

	}

}
