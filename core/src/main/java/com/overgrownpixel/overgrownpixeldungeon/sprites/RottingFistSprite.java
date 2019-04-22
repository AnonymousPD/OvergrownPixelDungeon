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

package com.overgrownpixel.overgrownpixeldungeon.sprites;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.watabou.noosa.Camera;
import com.watabou.noosa.TextureFilm;

public class RottingFistSprite extends MobSprite {
	
	private static final float FALL_SPEED	= 64;
	
	public RottingFistSprite() {
		super();
		
		texture( Assets.ROTTING );
		
		TextureFilm frames = new TextureFilm( texture, 24, 17 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 1 );
		
		run = new Animation( 3, true );
		run.frames( frames, 0, 1 );
		
		attack = new Animation( 2, false );
		attack.frames( frames, 0 );
		
		die = new Animation( 10, false );
		die.frames( frames, 0, 2, 3, 4 );
		
		play( idle );
	}
	
	@Override
	public void attack( int cell ) {
		super.attack( cell );
		
		speed.set( 0, -FALL_SPEED );
		acc.set( 0, FALL_SPEED * 4 );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		super.onComplete( anim );
		if (anim == attack) {
			speed.set( 0 );
			acc.set( 0 );
			place( ch.pos );
			
			Camera.main.shake( 4, 0.2f );
		}
	}
}
