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

package com.overgrownpixel.overgrownpixeldungeon.actors.blobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Dehydrated;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Levitation;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Vertigo;
import com.overgrownpixel.overgrownpixeldungeon.effects.BlobEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;

public class HotSteam extends Blob {

	@Override
	protected void evolve() {
		super.evolve();

		Char ch;
		int cell;

        Freezing freeze = (Freezing)Dungeon.level.blobs.get( Freezing.class );

		for (int i = area.left; i < area.right; i++){
			for (int j = area.top; j < area.bottom; j++){
				cell = i + j*Dungeon.level.width();
				if (cur[cell] > 0 && (ch = Actor.findChar( cell )) != null) {

				    if (freeze != null && freeze.volume > 0 && freeze.cur[cell] > 0){
                        freeze.clear(cell);
                        off[cell] = cur[cell] = 0;
                        continue;
                    }

					if (!ch.isImmune(this.getClass()))
                        Buff.affect( ch, Levitation.class, Levitation.DURATION/2 );
                        Buff.affect( ch, Vertigo.class, Levitation.DURATION/2 );
                        Buff.affect( ch, Dehydrated.class, Dehydrated.DURATION );
				}
			}
		}
	}

	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );

		emitter.pour( Speck.factory(Speck.STEAM), 1f );
	}

	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
