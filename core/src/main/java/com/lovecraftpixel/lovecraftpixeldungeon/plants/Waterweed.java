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

package com.lovecraftpixel.lovecraftpixeldungeon.plants;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Piranha;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Pushing;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.WaterweedPoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.PathFinder;

public class Waterweed extends Plant {

	{
		image = 32;
	}

	@Override
	public void activate( Char ch ) {

        int piras = 1;
        for(int i : PathFinder.NEIGHBOURS8){
            int cell = ch.pos + i;
            int terr = Dungeon.level.map[cell];
            if (terr == Terrain.EMPTY || terr == Terrain.GRASS ||
                    terr == Terrain.EMBERS || terr == Terrain.EMPTY_SP ||
                    terr == Terrain.HIGH_GRASS || terr == Terrain.FURROWED_GRASS
                    || terr == Terrain.EMPTY_DECO) {
                Level.set(cell, Terrain.WATER);
                GameScene.updateMap(cell);
            } else if (terr == Terrain.SECRET_TRAP || terr == Terrain.TRAP || terr == Terrain.INACTIVE_TRAP) {
                Level.set(cell, Terrain.WATER);
                Dungeon.level.traps.remove(cell);
                GameScene.updateMap(cell);
            }
            if(piras >= 0){
                piras--;
                Piranha piranha = new Piranha();
                piranha.pos = cell;

                GameScene.add( piranha );
                Actor.addDelayed( new Pushing( piranha, pos, piranha.pos ), -1 );
                piranha.aggro(ch);
            }

        }
	}

	public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_WATERWEED;

			plantClass = Waterweed.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return WaterweedPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
