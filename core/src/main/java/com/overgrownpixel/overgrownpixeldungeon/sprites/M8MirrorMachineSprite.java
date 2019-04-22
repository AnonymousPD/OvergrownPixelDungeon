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
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;

public class M8MirrorMachineSprite extends MobSprite {

	public M8MirrorMachineSprite() {
		super();
		
		texture( Assets.M8 );
		
		TextureFilm frames = new TextureFilm( texture, 22, 20 );
		
		idle = new Animation( 18, true );
		idle.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8 );
		
		run = new Animation( 18, true );
		run.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8 );
		
		attack = new Animation( 18, false );
		attack.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8 );
		
		die = new Animation( 18, false );
		die.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8 );
		
		play( idle );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		
		super.onComplete( anim );
		
		if (anim == die) {
			emitter().burst( Speck.factory( Speck.WOOL ), 15 );
		}
		if(anim == idle) {
            emitter().burst( Speck.factory( Speck.SMOKE ), 1 );
        }
	}

    @Override
    public int blood() {
        return 0x4D4D4D;
    }
}
