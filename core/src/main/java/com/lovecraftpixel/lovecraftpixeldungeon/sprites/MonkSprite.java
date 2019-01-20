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

package com.lovecraftpixel.lovecraftpixeldungeon.sprites;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Random;

public class MonkSprite extends MobSprite {
	
	private Animation kick;
	
	public MonkSprite() {
		super();
		
		texture( Assets.MONK );
		
		TextureFilm frames = new TextureFilm( texture, 15, 14 );
		
		idle = new Animation( 6, true );
		idle.frames( frames, 1, 0, 1, 2 );
		
		run = new Animation( 15, true );
		run.frames( frames, 11, 12, 13, 14, 15, 16 );
		
		attack = new Animation( 12, false );
		attack.frames( frames, 3, 4, 3, 4 );
		
		kick = new Animation( 10, false );
		kick.frames( frames, 5, 6, 5 );
		
		die = new Animation( 15, false );
		die.frames( frames, 1, 7, 8, 8, 9, 10 );
		
		play( idle );
	}
	
	@Override
	public void attack( int cell ) {
		super.attack( cell );
		if (Random.Float() < 0.5f) {
			play( kick );
		}
	}
	
	@Override
	public void onComplete( Animation anim ) {
		super.onComplete( anim == kick ? attack : anim );
	}
}
