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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Shaman;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Lightning;
import com.watabou.noosa.TextureFilm;

public class ShamanSprite extends MobSprite {
	
	public ShamanSprite() {
		super();
		
		texture( Assets.SHAMAN );
		
		TextureFilm frames = new TextureFilm( texture, 12, 15 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 0, 1, 0, 0, 1, 1 );
		
		run = new Animation( 12, true );
		run.frames( frames, 4, 5, 6, 7 );
		
		attack = new Animation( 12, false );
		attack.frames( frames, 2, 3, 0 );
		
		zap = attack.clone();
		
		die = new Animation( 12, false );
		die.frames( frames, 8, 9, 10 );
		
		play( idle );
	}
	
	public void zap( int pos ) {

		parent.add( new Lightning( ch.pos, pos, (Shaman)ch ) );
		
		turnTo( ch.pos, pos );
		play( zap );
	}
}
