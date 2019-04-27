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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.MusclemossPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Musclemoss extends Plant {

	{
		image = 18;
	}

	@Override
	public void activate( Char ch ) {
        int opposite;
        int path;
        do{
            path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
            opposite = pos + path;
        } while ((Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO)
                && (Dungeon.level.map[opposite+path] == Terrain.WALL || Dungeon.level.map[opposite+path] == Terrain.WALL_DECO));
        Ballistica trajectory = new Ballistica(pos, opposite, Ballistica.MAGIC_BOLT);
        WandOfBlastWave.throwChar(ch, trajectory, 100);
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
        if(Actor.findChar(pos) != null){
            int opposite;
            int path;
            do{
                path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                opposite = pos + path;
            } while ((Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO)
                    && (Dungeon.level.map[opposite+path] == Terrain.WALL || Dungeon.level.map[opposite+path] == Terrain.WALL_DECO));
            Ballistica trajectory = new Ballistica(pos, opposite, Ballistica.MAGIC_BOLT);
            WandOfBlastWave.throwChar(Actor.findChar(pos), trajectory, 100);
        }
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        int opposite;
        int path;
        do{
            path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
            opposite = enemy.pos + path;
        } while ((Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO)
                && (Dungeon.level.map[opposite+path] == Terrain.WALL || Dungeon.level.map[opposite+path] == Terrain.WALL_DECO));
        Ballistica trajectory = new Ballistica(enemy.pos, opposite, Ballistica.MAGIC_BOLT);
        WandOfBlastWave.throwChar(Actor.findChar(pos), trajectory, 100);
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_MUSCLEMOSS;

			plantClass = Musclemoss.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return MusclemossPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
