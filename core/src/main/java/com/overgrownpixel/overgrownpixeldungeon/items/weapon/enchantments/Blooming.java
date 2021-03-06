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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Blooming extends Weapon.Enchantment {

    private static Glowing DARK_GREEN = new Glowing( 0x008800 );

    @Override
    public int proc(Weapon weapon, Char attacker, Char defender, int damage) {

        // lvl 0 - 33%
        // lvl 1 - 50%
        // lvl 2 - 60%
        int level = Math.max( 0, weapon.level() );

        if (Random.Int( level + 3 ) >= 2) {

            if (!plantGrass(defender.pos)){
                ArrayList<Integer> positions = new ArrayList<>();
                for (int i : PathFinder.NEIGHBOURS8){
                    positions.add(i);
                }
                Random.shuffle( positions );
                for (int i : positions){
                    if (plantGrass(defender.pos + i)){
                        break;
                    }
                }
            }

        }

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

        return damage;
    }

    private boolean plantGrass(int cell){
        int c = Dungeon.level.map[cell];
        if ( c == Terrain.EMPTY || c == Terrain.EMPTY_DECO
                || c == Terrain.EMBERS || c == Terrain.GRASS){
            Level.set(cell, Terrain.HIGH_GRASS);
            GameScene.updateMap(cell);
            CellEmitter.get( cell ).burst( LeafParticle.LEVEL_SPECIFIC, 4 );
            return true;
        }
        return false;
    }
	
	@Override
	public Glowing glowing() {
		return DARK_GREEN;
	}
}
