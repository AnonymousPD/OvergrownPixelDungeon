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

import com.lovecraftpixel.lovecraftpixeldungeon.Challenges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Gold;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Heap;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.keys.IronKey;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;

public class CryptRoom extends SpecialRoom {

	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY );

		Point c = center();
		int cx = c.x;
		int cy = c.y;
		
		Door entrance = entrance();
		
		entrance.set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
		
		if (entrance.x == left) {
			Painter.set( level, new Point( right-1, top+1 ), Terrain.STATUE );
			Painter.set( level, new Point( right-1, bottom-1 ), Terrain.STATUE );
			cx = right - 2;
		} else if (entrance.x == right) {
			Painter.set( level, new Point( left+1, top+1 ), Terrain.STATUE );
			Painter.set( level, new Point( left+1, bottom-1 ), Terrain.STATUE );
			cx = left + 2;
		} else if (entrance.y == top) {
			Painter.set( level, new Point( left+1, bottom-1 ), Terrain.STATUE );
			Painter.set( level, new Point( right-1, bottom-1 ), Terrain.STATUE );
			cy = bottom - 2;
		} else if (entrance.y == bottom) {
			Painter.set( level, new Point( left+1, top+1 ), Terrain.STATUE );
			Painter.set( level, new Point( right-1, top+1 ), Terrain.STATUE );
			cy = top + 2;
		}
		
		level.drop( prize( level ), cx + cy * level.width() ).type = Heap.Type.TOMB;
	}
	
	private static Item prize( Level level ) {
		
		//1 floor set higher than normal
		Armor prize = Generator.randomArmor( (Dungeon.depth / 5) + 1);
		
		if (Challenges.isItemBlocked(prize)){
			return new Gold().random();
		}

		//if it isn't already cursed, give it a free upgrade
		if (!prize.cursed){
			prize.upgrade();
			//curse the armor, unless it has a glyph
			if (!prize.hasGoodGlyph()){
				prize.inscribe(Armor.Glyph.randomCurse());
			}
		}
		prize.cursed = prize.cursedKnown = true;
		
		return prize;
	}
}
