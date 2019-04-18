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

package com.lovecraftpixel.lovecraftpixeldungeon.levels.rooms.special;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Gold;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Heap;
import com.lovecraftpixel.lovecraftpixeldungeon.items.keys.IronKey;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.painters.Painter;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.plates.PressurePlateTreasury;
import com.watabou.utils.Random;

public class TreasuryRoom extends SpecialRoom {

	public void paint( final Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );
		
		Painter.set( level, center(), Terrain.STATUE );

        PressurePlateTreasury pressurePlateTreasury = new PressurePlateTreasury();

        int platepos;
        do {
            platepos = level.pointToCell(random());
        } while (level.map[platepos] != Terrain.EMPTY);
        level.setPlate(pressurePlateTreasury, platepos);
        Painter.set(level, platepos, Terrain.EMPTY);
		
		Heap.Type heapType = Random.Int( 2 ) == 0 ? Heap.Type.CHEST : Heap.Type.HEAP;
		
		int n = Random.IntRange( 2, 3 );
		for (int i=0; i < n; i++) {
			int pos;
			do {
				pos = level.pointToCell(random());
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null || pos == platepos);
			level.drop( new Gold().random(), pos ).type = (Random.Int(20) == 0 && heapType == Heap.Type.CHEST ? Heap.Type.MIMIC : heapType);
		}
		
		if (heapType == Heap.Type.HEAP) {
			for (int i=0; i < 6; i++) {
				int pos;
				do {
					pos = level.pointToCell(random());
				} while (level.map[pos] != Terrain.EMPTY || pos == platepos);
				level.drop( new Gold( Random.IntRange( 5, 12 ) ), pos );
			}
		}
		
		entrance().set( Door.Type.TUNNEL );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
	}
}
