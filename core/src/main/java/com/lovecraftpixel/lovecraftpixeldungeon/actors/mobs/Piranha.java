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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.Statistics;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Vertigo;
import com.lovecraftpixel.lovecraftpixeldungeon.items.food.MysteryMeat;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.RegularLevel;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.rooms.Room;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.rooms.special.PoolRoom;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Steamweed;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Waterweed;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.PiranhaSprite;
import com.watabou.utils.Random;

import java.util.Arrays;
import java.util.HashSet;

public class Piranha extends Mob {
	
	{
		spriteClass = PiranhaSprite.class;

		baseSpeed = 2f;
		
		EXP = 0;
		
		loot = MysteryMeat.class;
		lootChance = 1f;
		
		HUNTING = new Hunting();
		
		properties.add(Property.BLOB_IMMUNE);

        beneficialPlants = new HashSet<Class>(Arrays.asList(
                Waterweed.class, Steamweed.class
        ));
	}
	
	public Piranha() {
		super();
		
		HP = HT = 10 + Dungeon.depth * 5;
		defenseSkill = 10 + Dungeon.depth * 2;
	}
	
	@Override
	protected boolean act() {
		
		if (!Dungeon.level.water[pos]) {
			die( null );
			sprite.killAndErase();
			return true;
		} else {
			return super.act();
		}
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( Dungeon.depth, 4 + Dungeon.depth * 2 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 20 + Dungeon.depth * 2;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, Dungeon.depth);
	}
	
	@Override
	public void die( Object cause ) {
		super.die( cause );
		
		Statistics.piranhasKilled++;
		Badges.validatePiranhasKilled();
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		
		if (rooted) {
			return false;
		}
		
		int step = Dungeon.findStep( this, pos, target,
			Dungeon.level.water,
			fieldOfView );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected boolean getFurther( int target ) {
		int step = Dungeon.flee( this, pos, target,
			Dungeon.level.water,
			fieldOfView );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	{
		immunities.add( Burning.class );
		immunities.add( Vertigo.class );
	}
	
	private class Hunting extends Mob.Hunting{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			boolean result = super.act(enemyInFOV, justAlerted);
			//this causes piranha to move away when a door is closed on them in a pool room.
			if (state == WANDERING && Dungeon.level instanceof RegularLevel){
				Room curRoom = ((RegularLevel)Dungeon.level).room(pos);
				if (curRoom instanceof PoolRoom) {
					target = Dungeon.level.pointToCell(curRoom.random(1));
				}
			}
			return result;
		}
	}
}
