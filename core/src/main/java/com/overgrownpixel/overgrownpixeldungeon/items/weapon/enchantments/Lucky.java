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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Lucky extends Weapon.Enchantment {

	private static ItemSprite.Glowing GREEN = new ItemSprite.Glowing( 0x00FF00 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		int level = Math.max( 0, weapon.level() );
		
		float zeroChance = 0.5f;
		
		Luck buff = attacker.buff(Luck.class);
		if (buff != null){
			zeroChance = buff.zeroChance;
		}
		
		if (Random.Float() >= zeroChance){
			
			if (buff != null) {
				buff.detach();
			}

            if(attacker instanceof Hero){
                if(((Hero) attacker).belongings.armor.glyph != null){
                    comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
                }
            }
			
			return 2*damage;
		} else {
			
			buff = Buff.affect(attacker, Luck.class);
			buff.zeroChance = zeroChance * (0.5f - (0.01f*level));

            if(attacker instanceof Hero){
                if(((Hero) attacker).belongings.armor.glyph != null){
                    comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
                }
            }
			
			return 0;
		}

	}

	@Override
	public Glowing glowing() {
		return GREEN;
	}
	
	
	public static class Luck extends Buff {
		
		float zeroChance;
		
		@Override
		public boolean act() {
			
			zeroChance += 0.01f;
			
			if (zeroChance >= 0.5f){
				detach();
			} else {
				spend(TICK);
			}
			
			return true;
		}
		
		private static final String CHANCE = "chance";
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			zeroChance = bundle.getFloat(CHANCE);
		}
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(CHANCE, zeroChance);
		}
		
	}
	
}
