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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.ToxicGas;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.LasherSprite;
import com.watabou.utils.Random;

public class Lasher extends Mob {

	{
		spriteClass = LasherSprite.class;

        HP = HT = 8;
        defenseSkill = 2;

        EXP = 0;

		loot = Generator.Category.SEED;
		lootChance = 0.05f;

		state = WANDERING = new Waiting();

		properties.add(Property.IMMOVABLE);
        properties.add(Property.PLANT);
	}

    public Lasher(){
        super();
        HP = HT = 8 + Dungeon.depth * 2;
        defenseSkill = 2 + Dungeon.depth * 2;
    }

	@Override
	public void damage(int dmg, Object src) {
		if (src instanceof Burning) {
			destroy();
			sprite.die();
		} else {
			super.damage(dmg, src);
		}
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	protected boolean getCloser(int target) {
		return true;
	}

	@Override
	protected boolean getFurther(int target) {
		return true;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 4);
	}

	@Override
	public int attackSkill( Char target ) {
		return 8;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(1, 2);
	}

	{
		immunities.add( ToxicGas.class );
	}

	private class Waiting extends Wandering{}
}
