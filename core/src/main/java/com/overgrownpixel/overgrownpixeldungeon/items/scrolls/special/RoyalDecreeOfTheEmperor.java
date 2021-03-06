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

package com.overgrownpixel.overgrownpixeldungeon.items.scrolls.special;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.Flare;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.HolyBomb;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.Scroll;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class RoyalDecreeOfTheEmperor extends Scroll {

	{
		initials = 13;
	}

	@Override
	public void doRead() {

        if(Dungeon.level != null){
            Sample.INSTANCE.play( Assets.SND_READ );
            if(!Dungeon.level.mobs.isEmpty()) {
                new Flare( 14, 88 ).color( 0x148800, true ).show( curUser.sprite, 3f );
                Invisibility.dispel();
                ArrayList<Mob> mobs = new ArrayList<>();
                for (Mob mob : Dungeon.level.mobs) {
                    mobs.add(mob);
                }
                for(int m = mobs.size()-1; m > 0; m--){
                    mobs.get(m).die(curUser);
                }
            } else {
                Bomb bomb = new HolyBomb();
                for(int i = curUser.STR*2; i > 0; i--){
                    int pos = Dungeon.level.randomRespawnCell();
                    if(pos != Dungeon.hero.pos){
                        bomb.explode(Dungeon.level.randomRespawnCell());
                    }
                }
            }
            GLog.s(Messages.get(this,"exterminatus"));
        }

        setKnown();

        readAnimation();
	}
	
	@Override
	public void empoweredRead() {
		doRead();
	}
	
	@Override
	public int price() {
		return isKnown() ? 40 * quantity : super.price();
	}
}
