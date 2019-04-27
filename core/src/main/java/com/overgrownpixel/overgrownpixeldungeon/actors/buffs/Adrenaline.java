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

package com.overgrownpixel.overgrownpixeldungeon.actors.buffs;

import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class Adrenaline extends FlavourBuff {
	
	{
		type = buffType.POSITIVE;
	}
	
	public static final float DURATION	= 10f;
	
	@Override
	public int icon() {
		return BuffIndicator.AMOK;
	}
	
	@Override
	public void tintIcon(Image icon) {
		greyIcon(icon, 5f, cooldown());
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}
