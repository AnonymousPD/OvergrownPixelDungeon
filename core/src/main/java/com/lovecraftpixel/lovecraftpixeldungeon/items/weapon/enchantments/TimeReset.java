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

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class TimeReset extends Weapon.Enchantment {

	private static Glowing BLUEISH = new Glowing( 0x00FF9F );

	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private ArrayList<Integer> mobpos = new ArrayList<Integer>();
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {

		if (Random.Boolean()) {

            if(mobs.isEmpty() && !mobpos.isEmpty()){
                mobpos.clear();
            }
            if(!mobs.isEmpty() && mobpos.isEmpty()){
                mobs.clear();
            }

            if(mobs.isEmpty() && mobpos.isEmpty()){
                for(Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
                    if(Dungeon.level.heroFOV[mob.pos]){
                        mobs.add(mob);
                        mobpos.add(mob.pos);
                    }
                }
            } else {
                for(int m1 = mobs.size()-1; m1 > 0; m1--){
                    if(mobs.get(m1) != null){
                        appear(mobs.get(m1), mobpos.get(m1));
                    }
                }
                mobs.clear();
                mobpos.clear();
            }

		}

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

		return damage;

	}

    public void appear( Char ch, int pos ) {

        ch.sprite.interruptMotion();

        ch.move( pos );
        ch.sprite.place( pos );

        if (ch.invisible == 0) {
            ch.sprite.alpha( 0 );
            ch.sprite.parent.add( new AlphaTweener( ch.sprite, 1, 0.4f ) );
        }

    }
	
	@Override
	public Glowing glowing() {
		return BLUEISH;
	}
}
