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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.alchemy;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Freezing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Slow;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.ExoticPotion;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class PotionOfBlizzard extends ExoticPotion {
	
	{
		initials = 23;
	}

    @Override
    public void shatter(int cell) {

	    if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        for (int offset : PathFinder.NEIGHBOURS9){
            if (!Dungeon.level.solid[cell+offset]) {

                GameScene.add(Blob.seed(cell + offset, 10, Freezing.class));
                if(Actor.findChar(cell) != null){
                    Buff.affect(Actor.findChar(cell), Slow.class, Slow.DURATION);
                    Buff.affect(Actor.findChar(cell), Weakness.class, Weakness.DURATION);
                }
                for (int extra : PathFinder.NEIGHBOURS9){
                    if (!Dungeon.level.solid[cell+offset+extra]) {

                        GameScene.add(Blob.seed(cell + offset + extra, 10, Freezing.class));
                        if(Actor.findChar(cell) != null){
                            Buff.affect(Actor.findChar(cell), Slow.class, Slow.DURATION);
                            Buff.affect(Actor.findChar(cell), Weakness.class, Weakness.DURATION);
                        }
                    }
                }

            }
        }
    }
}
