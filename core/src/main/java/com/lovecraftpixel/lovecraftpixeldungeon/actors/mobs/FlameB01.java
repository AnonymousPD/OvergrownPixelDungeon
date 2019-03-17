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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.bombs.Bomb;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Firefoxglove;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.FlameBoiSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class FlameB01 extends Mob {

    private static final float TIME_TO_BURN	= 1f;

    public int gasTankPressure;
	
	{
		spriteClass = FlameBoiSprite.class;
        properties.add(Property.INORGANIC);
		
		HP = HT = 40;
		defenseSkill = 15;
		
		EXP = 10;
		maxLvl = 15;

		gasTankPressure = Random.Int(100, 150);
	}

    @Override
    protected boolean act() {
        if(buff(Burning.class) != null){
            new Bomb().explode(this.pos);
            this.die(Burning.class);
            return true;
        }
        return super.act();
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
    }

    @Override
    protected boolean doAttack( Char enemy ) {

        if (Dungeon.level.distance( pos, enemy.pos ) <= 1 || gasTankPressure <= 0) {

            return super.doAttack( enemy );

        } else {

            boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
            if (visible) {
                sprite.attack( enemy.pos );
                spend( TIME_TO_BURN );
                new Firefoxglove().shoot(this, enemy.pos);
                gasTankPressure -= Random.Int(1, 10);
            }

            return !visible;
        }
    }

    @Override
    protected Char chooseEnemy() {
	    if(super.chooseEnemy() instanceof Hero || super.chooseEnemy() instanceof Bat){
	        return super.chooseEnemy();
        } else {
	        this.state = WANDERING;
	       return null;
        }
    }

    @Override
	public int attackSkill( Char target ) {
		return 15;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 8);
	}

    @Override
    public String description() {
        return Messages.get(this, "desc", gasTankPressure);
    }

    private static final String PRESSURE     = "pressure";

    @Override
    public void storeInBundle(Bundle bundle) {
	    bundle.put(PRESSURE, gasTankPressure);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        gasTankPressure = bundle.getInt(PRESSURE);
        super.restoreFromBundle(bundle);
    }
}
