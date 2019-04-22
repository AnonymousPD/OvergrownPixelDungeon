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

package com.overgrownpixel.overgrownpixeldungeon.levels.rooms.standard;

import com.overgrownpixel.overgrownpixeldungeon.items.Gold;
import com.overgrownpixel.overgrownpixeldungeon.items.Heap;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Random;

public class SuspiciousChestRoom extends EmptyRoom {

	@Override
	public int minWidth() {
		return Math.max(5, super.minWidth());
	}

	@Override
	public int minHeight() {
		return Math.max(5, super.minHeight());
	}

	@Override
	public void paint(Level level) {
		super.paint(level);

		Item i = level.findPrizeItem();

		if ( i == null ){
			i = new Gold().random();
		}

		int center = level.pointToCell(center());

		Painter.set(level, center, Terrain.PEDESTAL);

		if (Random.Int(3) == 0) {
			level.drop(i, center).type = Heap.Type.MIMIC;
		} else {
			level.drop(i, center).type = Heap.Type.CHEST;
		}
	}
}
