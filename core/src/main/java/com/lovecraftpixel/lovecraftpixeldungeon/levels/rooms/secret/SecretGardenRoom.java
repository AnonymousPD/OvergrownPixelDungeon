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

package com.lovecraftpixel.lovecraftpixeldungeon.levels.rooms.secret;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Foliage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfRegrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Patch;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.painters.Painter;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Starflower;
import com.watabou.utils.Random;

public class SecretGardenRoom extends SecretRoom {
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.GRASS );
		
		boolean[] grass = Patch.generate(width()-2, height()-2, 0.5f, 0, true);
		for (int i=top + 1; i < bottom; i++) {
			for (int j=left + 1; j < right; j++) {
				if (grass[xyToPatchCoords(j, i)]) {
					level.map[i * level.width() + j] = Terrain.HIGH_GRASS;
				}
			}
		}
		
		entrance().set( Door.Type.HIDDEN );
		
		level.plant(new Starflower.Seed(), plantPos(level));
		level.plant(new WandOfRegrowth.Seedpod.Seed(), plantPos( level ));
		level.plant(new WandOfRegrowth.Dewcatcher.Seed(), plantPos( level ));
		
		if (Random.Int(2) == 0){
			level.plant(new WandOfRegrowth.Seedpod.Seed(), plantPos( level ));
		} else {
			level.plant(new WandOfRegrowth.Dewcatcher.Seed(), plantPos( level ));
		}
		
		Foliage light = (Foliage)level.blobs.get( Foliage.class );
		if (light == null) {
			light = new Foliage();
		}
		for (int i=top + 1; i < bottom; i++) {
			for (int j=left + 1; j < right; j++) {
				light.seed( level, j + level.width() * i, 1 );
			}
		}
		level.blobs.put( Foliage.class, light );
	}
	
	private int plantPos( Level level ){
		int pos;
		do{
			pos = level.pointToCell(random());
		} while (level.plants.get(pos) != null);
		return pos;
	}
	
	protected int xyToPatchCoords(int x, int y){
		return (x-left-1) + ((y-top-1) * (width()-2));
	}
}
