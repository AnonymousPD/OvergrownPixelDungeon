/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Invisibility;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Paralysis;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Terror;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Flare;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfTerror extends Scroll {

	{
		initials = 9;
	}

	@Override
	public void doRead() {
		
		new Flare( 5, 32 ).color( 0xFF0000, true ).show( curUser.sprite, 2f );
		Sample.INSTANCE.play( Assets.SND_READ );
		Invisibility.dispel();
		
		int count = 0;
		Mob affected = null;
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				Buff.affect( mob, Terror.class, 20f ).object = curUser.id();

				if (mob.buff(Terror.class) != null){
					count++;
					affected = mob;
				}
			}
		}
		
		switch (count) {
		case 0:
			GLog.i( Messages.get(this, "none") );
			break;
		case 1:
			GLog.i( Messages.get(this, "one", affected.name) );
			break;
		default:
			GLog.i( Messages.get(this, "many") );
		}
		setKnown();

		readAnimation();
	}
	
	@Override
	public void empoweredRead() {
		doRead();
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				Terror t = mob.buff(Terror.class);
				if (t != null){
					Buff.prolong(mob, Terror.class, 15f);
					Buff.affect(mob, Paralysis.class, 5f);
				}
			}
		}
	}
	
	@Override
	public int price() {
		return isKnown() ? 40 * quantity : super.price();
	}
}
