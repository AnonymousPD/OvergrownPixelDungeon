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

package com.overgrownpixel.overgrownpixeldungeon.effects.particles;

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class ChaosParticle extends PixelParticle {

	public static final Factory FACTORY = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((ChaosParticle)emitter.recycle( ChaosParticle.class )).reset( x, y );
		}
		@Override
		public boolean lightMode() {
			return true;
		};
	};

	public ChaosParticle() {
		super();
		
		color(Random.Int(0xFF0000, 0x00FF1E));
        lifespan = 1.2f;
        speed.set( 0, -6 );
	}

    private float offs;
	
	public void reset( float x, float y ) {
        revive();

        this.x = x;
        this.y = y;

        offs = -Random.Float( lifespan );
        left = lifespan - offs;
        color(Random.Int(Random.Int(0xFF0000, 0x00FF1E), Random.Int(0xFF00DB, 0x00FFEC)));
	}
	
	@Override
	public void update() {
		super.update();
        float p = left / lifespan;
        am = p < 0.5f ? p : 1 - p;
        scale.x = (1 - p) * 4;
        scale.y = 16 + (1 - p) * 16;
	}
}