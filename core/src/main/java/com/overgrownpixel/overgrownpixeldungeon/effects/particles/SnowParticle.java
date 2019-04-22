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

public class SnowParticle extends PixelParticle {
	
	public static final Emitter.Factory FACTORY = new Factory() {
		@Override
		public void emit( Emitter emitter, int index, float x, float y ) {
			((SnowParticle)emitter.recycle( SnowParticle.class )).reset( x, y );
		}
	};
	
	public SnowParticle() {
		super();
		speed.set( 0, Random.Float( 5, 8 ) );
		lifespan = 1.2f;
	}
	
	public void reset( float x, float y ) {
		revive();
		
		this.x = x;
		this.y = y - speed.y * lifespan;
		
		left = lifespan;
	}
	
	@Override
	public void update() {
		super.update();
		float p = left / lifespan;
		am = (p < 0.5f ? p : 1 - p) * 1.5f;
	}
}