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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;

public class EarthquakeDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_EARTHQUAKE;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {

        if (Dungeon.level.heroFOV[defender.pos]) {
            CellEmitter.get( defender.pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 3, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );
        }
        defender.damage(defender.damageRoll(), this);
		
		return super.proc(attacker, defender, damage);
	}
}
