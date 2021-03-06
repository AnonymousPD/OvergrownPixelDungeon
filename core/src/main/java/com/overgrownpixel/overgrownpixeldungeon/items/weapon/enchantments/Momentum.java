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
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Momentum extends Weapon.Enchantment {

	private static Glowing PURPLEISH = new Glowing( 0xAF57B0 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		int level = Math.max( 0, weapon.level() );
		
		if (Random.Int( level + 3 ) >= 2) {
		    if(attacker instanceof Hero){
                for(Mob mob : Dungeon.level.mobs){
                    if(Dungeon.level.distance(attacker.pos, mob.pos) <= weapon.level() && Dungeon.level.heroFOV[mob.pos]){

                        int opposite;
                        int path;
                        do{
                            path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                            opposite = mob.pos + path;
                        } while ((Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO)
                                && (Dungeon.level.map[opposite+path] == Terrain.WALL || Dungeon.level.map[opposite+path] == Terrain.WALL_DECO));
                        Ballistica trajectory = new Ballistica(mob.pos, opposite, Ballistica.MAGIC_BOLT);
                        WandOfBlastWave.throwChar(Actor.findChar(mob.pos), trajectory, 100);

                        ScrollOfTeleportation.teleportToLocation((Hero) attacker, mob.pos);
                        GLog.p(Messages.get(this, "headbutt", mob.name));
                    }
                }
            } else {
                int opposite;
                int path;
                do{
                    path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                    opposite = defender.pos + path;
                } while ((Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO)
                        && (Dungeon.level.map[opposite+path] == Terrain.WALL || Dungeon.level.map[opposite+path] == Terrain.WALL_DECO));
                Ballistica trajectory = new Ballistica(defender.pos, opposite, Ballistica.MAGIC_BOLT);
                WandOfBlastWave.throwChar(Actor.findChar(defender.pos), trajectory, 100);
                ScrollOfTeleportation.appear(attacker, defender.pos);
            }
		}

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

		return damage;

	}
	
	@Override
	public Glowing glowing() {
		return PURPLEISH;
	}
}
