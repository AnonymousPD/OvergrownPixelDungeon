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

package com.lovecraftpixel.lovecraftpixeldungeon.tiles;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;

public class RaisedTerrainTilemap extends DungeonTilemap {
	
	public RaisedTerrainTilemap() {
		super(Dungeon.level.tilesTex());
		map( Dungeon.level.map, Dungeon.level.width() );
	}
	
	@Override
	protected int getTileVisual(int pos, int tile, boolean flat) {
		
		if (flat) return -1;
		
		if (tile == Terrain.HIGH_GRASS){
			return DungeonTileSheet.getVisualWithAlts(
					DungeonTileSheet.RAISED_HIGH_GRASS,
					pos) + 2;
		} else if (tile == Terrain.FURROWED_GRASS){
			return DungeonTileSheet.getVisualWithAlts(
					DungeonTileSheet.RAISED_FURROWED_GRASS,
					pos) + 2;
		}
		
		
		return -1;
	}
	
	@Override
	protected boolean needsRender(int pos) {
		return data[pos] != -1;
	}
}
