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

package com.lovecraftpixel.lovecraftpixeldungeon.items.potions.alchemy;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.Potion;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class PotionOfEruption extends Potion {

	{
		initials = 20;
	}

    @Override
    public void shatter(int cell) {

        if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

	    for(int p : PathFinder.NEIGHBOURS9){
            Actor actor = Actor.findChar(cell+p);
            if(actor instanceof Mob){
                ((Mob) actor).damage(((Mob) actor).damageRoll(), this);
            } else {
                ((Char) actor).damage(((Char) actor).damageRoll(), this);
            }
            if (Dungeon.level.heroFOV[cell+p]) {
                CellEmitter.get( cell+p ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
                Camera.main.shake( 3, 0.7f );
                Sample.INSTANCE.play( Assets.SND_ROCKS );
            }
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
