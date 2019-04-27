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

package com.overgrownpixel.overgrownpixeldungeon.items.armor.curses;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Vertigo;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor.Glyph;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;

public class Featherweight extends Glyph {

	private static Glowing BLACK = new Glowing( 0x000000 );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage) {

        int oppositeAttacker = defender.pos + (defender.pos - attacker.pos);
        Ballistica trajectory = new Ballistica(defender.pos, oppositeAttacker, Ballistica.MAGIC_BOLT);
        WandOfBlastWave.throwChar(defender, trajectory, 2);
        Buff.affect(defender, Vertigo.class, Vertigo.DURATION/2);

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.weapon instanceof Weapon){
                if(((Weapon) ((Hero) attacker).belongings.weapon).enchantment != null){
                    Weapon.Enchantment.comboProc(((Weapon) ((Hero) attacker).belongings.weapon).enchantment, this, attacker, defender, damage);
                }
            }
        }

		return damage;
	}

	@Override
	public Glowing glowing() {
		return BLACK;
	}

	@Override
	public boolean curse() {
		return true;
	}
}
