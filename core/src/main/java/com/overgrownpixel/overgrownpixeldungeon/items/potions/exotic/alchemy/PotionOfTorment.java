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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Wraith;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.ExoticPotion;
import com.watabou.noosa.audio.Sample;

public class PotionOfTorment extends ExoticPotion {
	
	{
		initials = 27;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        for(Mob mob : Dungeon.level.mobs){
            if(Dungeon.level.heroFOV[mob.pos]){
                Wraith.spawnAt(mob.pos);
                mob.die(hero);
            }
        }
    }

    @Override
    public void shatter(int cell) {

	    if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        if(Actor.findChar(cell) != null){
            Actor.findChar(cell).die(this);
            Wraith.spawnAt(cell);
        }
    }
}
