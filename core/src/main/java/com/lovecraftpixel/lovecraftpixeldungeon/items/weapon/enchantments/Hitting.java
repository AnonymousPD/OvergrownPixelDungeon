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

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;

public class Hitting extends Weapon.Enchantment {

	private static Glowing PURPLE = new Glowing( 0x96173D );

	private static ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		int level = Math.max( 0, weapon.level() );

		boolean extradamage = false;
		
		if (Random.Int( level + 3 ) >= 2) {
			
            if(defender instanceof Mob){
                if(mobs.contains(defender)){
                    extradamage = true;
                } else {
                    mobs.add((Mob) defender);
                }
            }
			
		}

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

        if(extradamage){
            return damage*2;
        }
		return damage;

	}
	
	@Override
	public Glowing glowing() {
		return PURPLE;
	}

	private static final String MOBS = "mobs";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(MOBS, mobs);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        Collection<Bundlable> collection = bundle.getCollection( MOBS );
        for (Bundlable m : collection) {
            Mob mob = (Mob)m;
            if (mob != null) {
                mobs.add( mob );
            }
        }
    }
}
