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

package com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.melee;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Bleeding;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;

public class KnifeGlove extends MeleeWeapon {

	{
		image = ItemSpriteSheet.KNIFE_GLOVE;

		tier = 1;
		
		bones = false;
	}

	@Override
	public int max(int lvl) {
		return  (int)(3f*(tier+1)) +    //6 base, down from 10
				lvl*tier;               //+1 per level, down from +2
	}

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        Buff.affect( defender, Bleeding.class ).set( Math.round(damage/4) );
        return super.proc(attacker, defender, Math.round(damage/1.2f));
    }
}
