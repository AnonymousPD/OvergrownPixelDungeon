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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BuffIndicator;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;

public class Midas extends FlavourBuff {

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)){
		    //all other buffs get removed
			for(Buff buffs : target.buffs()){
			    if(!(buffs instanceof Midas)){
			        Buff.detach(target, buffs.getClass());
                }
            }
            if(!(target instanceof Hero)){
                target.paralysed++;
            } else {
                Buff.prolong( target, Slow.class, Slow.DURATION );
                GLog.w( Messages.get(this, "infuse") );
            }
			return true;
		} else {
			return false;
		}
	}

    @Override
    public void detach() {
        super.detach();
        if (target.paralysed > 0)
            target.paralysed--;
    }

	@Override
	public int icon() {
		return BuffIndicator.MIDAS;
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.GOLD);
		else target.sprite.remove(CharSprite.State.GOLD);
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc");
	}
}
