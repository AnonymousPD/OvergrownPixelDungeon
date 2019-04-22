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
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.particles.Emitter;

public class RotHeartSprite extends MobSprite {

	private Emitter cloud;

	public RotHeartSprite(){
		super();

		perspectiveRaise = 0.2f;

		texture( Assets.ROT_HEART );

		TextureFilm frames = new TextureFilm( texture, 16, 16 );

		idle = new MovieClip.Animation( 1, true );
		idle.frames( frames, 0);

		run = new MovieClip.Animation( 1, true );
		run.frames( frames, 0 );

		attack = new MovieClip.Animation( 1, false );
		attack.frames( frames, 0 );

		die = new MovieClip.Animation( 8, false );
		die.frames( frames, 1, 2, 3, 4, 5, 6, 7, 7, 7 );

		play( idle );
	}

	@Override
	public void link( Char ch ) {
		super.link( ch );

		renderShadow = false;

		if (cloud == null) {
			cloud = emitter();
			cloud.pour( Speck.factory(Speck.TOXIC), 0.7f );
		}
	}

	@Override
	public void turnTo(int from, int to) {
		//do nothing
	}

	@Override
	public void update() {

		super.update();

		if (cloud != null) {
			cloud.visible = visible;
		}
	}

	@Override
	public void die() {
		super.die();

		if (cloud != null) {
			cloud.on = false;
		}
	}
}
