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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class HealthDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_HEALTH;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {
		
		//heals 60 hp at base, scaling with enemy HT
		Buff.affect( defender, Healing.class ).setHeal((int)(0.5f*defender.HT + 60), 0.25f, 0);
		PotionOfHealing.cure( defender );
		
		if (attacker.alignment == defender.alignment){
			return 0;
		}
		
		return super.proc(attacker, defender, damage);
	}
	
}
