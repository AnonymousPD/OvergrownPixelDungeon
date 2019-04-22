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

package com.overgrownpixel.overgrownpixeldungeon.levels.rooms.connection;

import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;
import com.overgrownpixel.overgrownpixeldungeon.levels.rooms.Room;
import com.watabou.utils.Rect;

public class RingBridgeRoom extends RingTunnelRoom {

	@Override
	public void paint(Level level) {
		Painter.fill(level, this, 1, Terrain.CHASM);

		super.paint(level);

		for (Room r : neigbours){
			if (r instanceof BridgeRoom || r instanceof RingBridgeRoom || r instanceof WalkwayRoom){
				Rect i = intersect(r);
				if (i.width() != 0){
					i.left++;
					i.right--;
				} else {
					i.top++;
					i.bottom--;
				}
				Painter.fill(level, i.left, i.top, i.width()+1, i.height()+1, Terrain.CHASM);
			}
		}
	}
}
