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

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Infested;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ParasiteshrubPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Random;

public class Parasiteshrub extends Plant {

	{
		image = 28;
	}

	@Override
	public void activate( Char ch ) {

	    if(ch.properties().contains(Char.Property.INORGANIC)){
            return;
        }

        Buff.affect( ch, Infested.class ).set(Random.Int(5, 7) );
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if(enemy.properties().contains(Char.Property.INORGANIC)){
            return;
        }

        Buff.affect( enemy, Infested.class ).set(Random.Int(4, 6) );
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_PARASITESHRUB;

			plantClass = Parasiteshrub.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return ParasiteshrubPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
