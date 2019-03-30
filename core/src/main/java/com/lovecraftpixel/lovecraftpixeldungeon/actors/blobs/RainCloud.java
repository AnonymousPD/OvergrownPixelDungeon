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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.BlobEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Waterweed;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;

public class RainCloud extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		int cell;
		
		for (int i = area.left; i < area.right; i++){
			for (int j = area.top; j < area.bottom; j++){
				cell = i + j*Dungeon.level.width();
				if (off[cell] > 0) {
					int terr = Dungeon.level.map[cell];
					if (terr == Terrain.EMPTY || terr == Terrain.GRASS ||
							terr == Terrain.EMBERS || terr == Terrain.EMPTY_SP ||
							terr == Terrain.HIGH_GRASS || terr == Terrain.FURROWED_GRASS
							|| terr == Terrain.EMPTY_DECO) {
						Level.set(cell, Terrain.WATER);
						GameScene.updateMap(cell);
						if(Random.Boolean() && Dungeon.level.plants.get(cell) != null){
                            new Waterweed.Seed().couch(cell, Dungeon.level);
                        }
					} else if (terr == Terrain.SECRET_TRAP || terr == Terrain.TRAP || terr == Terrain.INACTIVE_TRAP) {
						Level.set(cell, Terrain.WATER);
						Dungeon.level.traps.remove(cell);
						GameScene.updateMap(cell);
                        if(Random.Boolean() && Dungeon.level.plants.get(cell) != null){
                            new Waterweed.Seed().couch(cell, Dungeon.level);
                        }
					}
				}
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour( Speck.factory( Speck.STORM ), 0.4f );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
	
}
