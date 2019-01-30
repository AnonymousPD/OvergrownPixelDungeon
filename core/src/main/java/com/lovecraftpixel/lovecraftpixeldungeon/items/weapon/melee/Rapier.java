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

import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;

public class Rapier extends MeleeWeapon {

	{
		image = ItemSpriteSheet.RAPIER;

		tier = 3;
		DLY = 0.25f; //4x speed
	}

	@Override
	public int max(int lvl) {
		return  2*(tier+1) +                    //8 base, down from 20
				lvl*Math.round(0.75f*(tier+1)); //+3 per level, down from +4
	}
}
