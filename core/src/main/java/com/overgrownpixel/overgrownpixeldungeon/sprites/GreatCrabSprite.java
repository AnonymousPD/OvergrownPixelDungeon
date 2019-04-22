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
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;

public class GreatCrabSprite extends MobSprite {

	public GreatCrabSprite() {
		super();

		texture( Assets.CRAB );

		TextureFilm frames = new TextureFilm( texture, 16, 16 );

		idle = new MovieClip.Animation( 5, true );
		idle.frames( frames, 16, 17, 16, 18 );

		run = new MovieClip.Animation( 10, true );
		run.frames( frames, 19, 20, 21, 22 );

		attack = new MovieClip.Animation( 12, false );
		attack.frames( frames, 23, 24, 25 );

		die = new MovieClip.Animation( 12, false );
		die.frames( frames, 26, 27, 28, 29 );

		play( idle );
	}

	@Override
	public int blood() {
		return 0xFFFFEA80;
	}
}
