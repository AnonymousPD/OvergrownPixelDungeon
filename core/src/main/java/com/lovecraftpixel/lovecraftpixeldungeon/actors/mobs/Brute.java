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

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Terror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Gold;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Apricobush;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Fadeleaf;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Musclemoss;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Nightshadeonion;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Starflower;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Sungrass;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Swiftthistle;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.BruteSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.Arrays;
import java.util.HashSet;

public class Brute extends Mob {
	
	{
		spriteClass = BruteSprite.class;
		
		HP = HT = 40;
		defenseSkill = 15;
		
		EXP = 8;
		maxLvl = 15;
		
		loot = Gold.class;
		lootChance = 0.5f;

        beneficialPlants = new HashSet<Class>(Arrays.asList(
                Sungrass.class, Starflower.class, Swiftthistle.class,
                Musclemoss.class, Nightshadeonion.class, Fadeleaf.class,
                Apricobush.class
        ));
	}
	
	private boolean enraged = false;
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		enraged = HP < HT / 4;
	}
	
	@Override
	public int damageRoll() {
		return enraged ?
			Random.NormalIntRange( 15, 45 ) :
			Random.NormalIntRange( 6, 26 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 20;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 8);
	}
	
	@Override
	public void damage( int dmg, Object src ) {
		super.damage( dmg, src );
		
		if (isAlive() && !enraged && HP < HT / 4) {
			enraged = true;
			spend( TICK );
			if (Dungeon.level.heroFOV[pos]) {
				sprite.showStatus( CharSprite.NEGATIVE, Messages.get(this, "enraged") );
			}
		}
	}
	
	{
		immunities.add( Terror.class );
	}
}
