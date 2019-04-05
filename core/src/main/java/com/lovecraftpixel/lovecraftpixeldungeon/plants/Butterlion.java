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

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.ButterlionPoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;

public class Butterlion extends Plant {

	{
		image = 17;
	}

	@Override
	public void activate( Char ch ) {

        CellEmitter.get( ch.pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
        Camera.main.shake( 3, 0.7f );
        Sample.INSTANCE.play( Assets.SND_ROCKS );
        ch.damage(ch.damageRoll(), this);

	}

    @Override
    public void activate() {
        CellEmitter.get( pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
        Camera.main.shake( 3, 0.7f );
        Sample.INSTANCE.play( Assets.SND_ROCKS );
    }

	public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BUTTERLION;

			plantClass = Butterlion.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return ButterlionPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
