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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs;

import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Disease;
import com.overgrownpixel.overgrownpixeldungeon.sprites.M8MirrorMachineSprite;

public class M8MirrorMachine extends NPC {

	{
		spriteClass = M8MirrorMachineSprite.class;
        properties.add(Property.INORGANIC);
	}

	@Override
	protected boolean act() {
        throwItem();
        return super.act();
	}

	@Override
	public void damage( int dmg, Object src ) {
	}

	@Override
	public void add( Buff buff ) {
	}

    @Override
    public synchronized void add(Disease disease) {
    }

    @Override
	public boolean interact() {
		//TODO: Do this
		return false;
	}
}