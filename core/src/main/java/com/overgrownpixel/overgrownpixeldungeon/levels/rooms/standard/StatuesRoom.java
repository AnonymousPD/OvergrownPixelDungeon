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

import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;

public class StatuesRoom extends StandardRoom {

	@Override
	public int minWidth() {
		return Math.max(7, super.minWidth());
	}

	@Override
	public int minHeight() {
		return Math.max(7, super.minHeight());
	}

	@Override
	public float[] sizeCatProbs() {
		return new float[]{9, 3, 1};
	}

	@Override
	public void paint(Level level) {
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1 , Terrain.EMPTY );

		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}


		int rows = (width() + 1)/6;
		int cols = (height() + 1)/6;

		int w = (width() - 4 - (rows-1))/rows;
		int h = (height() - 4 - (cols-1))/cols;

		int Wspacing = rows % 2 == width() % 2 ? 2 : 1;
		int Hspacing = cols % 2 == height() % 2 ? 2 : 1;

		for (int x = 0; x < rows; x++){
			for (int y = 0; y < cols; y++){
				int left = this.left + 2 + (x * (w + Wspacing));
				int top = this.top + 2 + (y * (h + Hspacing));

				Painter.fill(level, left, top, w, h, Terrain.EMPTY_SP);

				Painter.set(level, left, top, Terrain.STATUE_SP);
				Painter.set(level, left + w-1, top, Terrain.STATUE_SP);
				Painter.set(level, left, top + h-1, Terrain.STATUE_SP);
				Painter.set(level, left + w-1, top + h-1, Terrain.STATUE_SP);
			}
		}

	}
}
