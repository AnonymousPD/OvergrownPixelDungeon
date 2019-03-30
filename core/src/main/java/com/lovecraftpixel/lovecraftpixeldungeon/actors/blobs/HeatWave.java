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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Dehydrated;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.BlobEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.FlameParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;

public class HeatWave extends Blob {

	@Override
	protected void evolve() {
        boolean[] flamable = Dungeon.level.flamable;
        int cell;
        int fire;

        Freezing freeze = (Freezing)Dungeon.level.blobs.get( Freezing.class );

        boolean observe = false;

        for (int i = area.left-1; i <= area.right; i++) {
            for (int j = area.top-1; j <= area.bottom; j++) {
                cell = i + j*Dungeon.level.width();
                if (cur[cell] > 0) {

                    if (freeze != null && freeze.volume > 0 && freeze.cur[cell] > 0){
                        freeze.clear(cell);
                        off[cell] = cur[cell] = 0;
                        continue;
                    }

                    burn( cell );

                    fire = cur[cell] - 1;
                    if (fire <= 0 && flamable[cell]) {

                        Dungeon.level.destroy( cell );

                        observe = true;
                        GameScene.updateMap( cell );

                    }

                } else if (freeze == null || freeze.volume <= 0 || freeze.cur[cell] <= 0) {

                    if (flamable[cell]
                            && (cur[cell-1] > 0
                            || cur[cell+1] > 0
                            || cur[cell-Dungeon.level.width()] > 0
                            || cur[cell+Dungeon.level.width()] > 0)) {
                        fire = 4;
                        burn( cell );
                        area.union(i, j);
                    } else {
                        fire = 0;
                    }

                } else {
                    fire = 0;
                }

                volume += (off[cell] = fire);
            }
        }

        if (observe) {
            Dungeon.observe();
        }
	}

    public static void burn( int pos ) {
        Char ch = Actor.findChar( pos );
        if (ch != null && !ch.isImmune(Dehydrated.class)) {
            Buff.prolong( ch, Dehydrated.class, Dehydrated.DURATION );
        }

        Plant plant = Dungeon.level.plants.get( pos );
        if (plant != null){
            plant.trigger();
        }
    }

	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour( FlameParticle.FACTORY, 2f );
	}

	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
