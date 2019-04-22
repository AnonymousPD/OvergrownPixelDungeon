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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Chill;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Frost;
import com.overgrownpixel.overgrownpixeldungeon.items.quest.Embers;
import com.overgrownpixel.overgrownpixeldungeon.sprites.NewbornElementalSprite;

public class NewbornElemental extends Elemental {

	{
		spriteClass = NewbornElementalSprite.class;

		HT = 65;
		HP = HT/2; //32

		defenseSkill = 12;

		EXP = 7;

		properties.add(Property.MINIBOSS);
	}

	@Override
	public void add(Buff buff) {
		if (buff instanceof Frost || buff instanceof Chill) {
			die(buff);
		} else {
			super.add(buff);
		}
	}

	@Override
	public void die(Object cause) {
		super.die(cause);
		Dungeon.level.drop( new Embers(), pos ).sprite.drop();
	}
}
