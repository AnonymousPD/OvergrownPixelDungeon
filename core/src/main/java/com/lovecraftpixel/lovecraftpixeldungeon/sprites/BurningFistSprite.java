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
import com.lovecraftpixel.lovecraftpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class BurningFistSprite extends MobSprite {
	
	public BurningFistSprite() {
		super();
		
		texture( Assets.BURNING );
		
		TextureFilm frames = new TextureFilm( texture, 24, 17 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 1 );
		
		run = new Animation( 3, true );
		run.frames( frames, 0, 1 );
		
		attack = new Animation( 8, false );
		attack.frames( frames, 0, 5, 6 );
		
		die = new Animation( 10, false );
		die.frames( frames, 0, 2, 3, 4 );
		
		play( idle );
	}
	
	private int posToShoot;
	
	@Override
	public void attack( int cell ) {
		posToShoot = cell;
		super.attack( cell );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == attack) {

			Sample.INSTANCE.play( Assets.SND_ZAP );
			MagicMissile.boltFromChar( parent,
					MagicMissile.SHADOW,
					this,
					posToShoot,
					new Callback() {
						@Override
						public void call() {
							ch.onAttackComplete();
						}
					} );

			idle();
			
		} else {
			super.onComplete( anim );
		}
	}
}
