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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.items.Dewdrop;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;

public class DewDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_DEW;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {

        for(int p: PathFinder.NEIGHBOURS8){
            if(Dungeon.level.passable[defender.pos]){
                Dungeon.level.drop(new Dewdrop(), defender.pos+p);
            }
        }
		
		return super.proc(attacker, defender, damage);
	}
}
