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

package com.overgrownpixel.overgrownpixeldungeon.items.stones;

import com.overgrownpixel.overgrownpixeldungeon.items.EquipableItem;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.Wand;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;

public class StoneOfDetectCurse extends InventoryStone {
	
	{
		mode = WndBag.Mode.CURSE_DETECTABLE;
		image = ItemSpriteSheet.STONE_CURSE;
	}
	
	@Override
	protected void onItemSelected(Item item) {
		
		item.cursedKnown = true;
		useAnimation();
		
		if (item.cursed){
			GLog.w( Messages.get(this, "cursed") );
		} else {
			GLog.w( Messages.get(this, "not_cursed") );
		}
	}
	
	public static boolean canDetectCurse(Item item){
		return !item.isIdentified()
				&& !item.cursedKnown
				&& (item instanceof EquipableItem || item instanceof Wand);
	}
}
