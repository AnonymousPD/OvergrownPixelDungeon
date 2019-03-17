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

package com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments;

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Whirlwind extends Weapon.Enchantment {

	private static ItemSprite.Glowing WHITEISH = new ItemSprite.Glowing( 0xddfbc2 );

	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
		// lvl 0 - 20%
		// lvl 1 - 33%
		// lvl 2 - 43%
		int level = Math.max( 0, weapon.level() );

		if (Random.Int( level + 5 ) >= 4) {
            attacker.sprite.emitter().burst( Speck.factory( Speck.EVOKE ), 3);

			for(int i : PathFinder.NEIGHBOURS8){
                if(Actor.findChar(attacker.pos+i) != null){
                    if(Actor.findChar(attacker.pos+i) instanceof Mob){
                        Actor.findChar(attacker.pos+i).damage(damage, attacker);
                    }
                }
            }

		}

		return damage;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return WHITEISH;
	}

}