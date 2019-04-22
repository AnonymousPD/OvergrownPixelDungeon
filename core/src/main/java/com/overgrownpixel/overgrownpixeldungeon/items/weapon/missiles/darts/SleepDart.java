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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FlavourBuff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Sleep;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class SleepDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.SLEEP_DART;
	}
	
	@Override
	public int proc(Char attacker, final Char defender, int damage) {
		
		//need to delay this so damage from the dart doesn't break the sleep
		new FlavourBuff(){
			{actPriority = VFX_PRIO;}
			public boolean act() {
				Buff.affect( defender, Sleep.class );
				return super.act();
			}
		}.attachTo(defender);
		
		return super.proc(attacker, defender, damage);
	}
}
