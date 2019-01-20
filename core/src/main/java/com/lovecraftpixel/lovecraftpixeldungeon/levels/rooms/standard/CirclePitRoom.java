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

package com.lovecraftpixel.lovecraftpixeldungeon.levels.rooms.standard;

import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.painters.Painter;

public class CirclePitRoom extends StandardRoom {

	@Override
	public int minWidth() {
		return Math.max(8, super.minWidth());
	}

	@Override
	public int minHeight() {
		return Math.max(8, super.minHeight());
	}

	@Override
	public float[] sizeCatProbs() {
		return new float[]{4, 2, 1};
	}

	@Override
	public void paint(Level level) {
		Painter.fill( level, this, Terrain.WALL );

		Painter.fillEllipse( level, this, 1 , Terrain.EMPTY );

		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
			if (door.x == left || door.x == right){
				Painter.drawInside(level, this, door, width()/2, Terrain.EMPTY);
			} else {
				Painter.drawInside(level, this, door, height()/2, Terrain.EMPTY);
			}
		}

		Painter.fillEllipse( level, this, 3 , Terrain.CHASM );
	}
}
