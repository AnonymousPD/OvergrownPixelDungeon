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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs;

import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.InfestedParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.rings.RingOfPoison;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BuffIndicator;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Infested extends Buff implements Hero.Doom {
	
	protected float left;
	
	private static final String LEFT	= "left";

	{
		type = buffType.NEGATIVE;
		announced = true;
	}
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEFT, left );
		
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		left = bundle.getFloat( LEFT );
	}
	
	public void set( float duration ) {
		this.left = Math.max(duration, left);
	}

	public void extend( float duration ) {
		this.left += duration;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.INFESTED;
	}
	
	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(0xA3FF00);
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String heroMessage() {
		return Messages.get(this, "heromsg");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(left));
	}

	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target) && target.sprite != null){
			CellEmitter.center(target.pos).burst( InfestedParticle.SPLASH, 5 );
			return true;
		} else
			return false;
	}

	@Override
	public boolean act() {
		if (target.isAlive()) {
			
			if(Random.Boolean()){
			    int dmg = (int)(left / 3) + 1;
                target.damage( dmg, this );
                CellEmitter.center(target.pos).burst( InfestedParticle.SPLASH, dmg );
                if(Random.Boolean()){
                    Buff.affect( target, Ooze.class ).set( 5f );
                }
            }
			if(target instanceof Hero){
                if(((Hero) target).belongings.misc1 instanceof RingOfPoison || ((Hero) target).belongings.misc2 instanceof RingOfPoison){
                    spendTicksPoison(true);
                } else {
                    spendTicksPoison(false);
                }
            } else {
                spendTicksPoison(false);
            }
			
		} else {
			
			detach();
			
		}
		
		return true;
	}

	private void spendTicksPoison(boolean doubleTickSpeed){
        spend( TICK );

        int multiplier = 1;
        if(doubleTickSpeed){
            multiplier = 2;
        }

        if ((left -= TICK*multiplier) <= 0) {
            detach();
        }
    }

	@Override
	public void onDeath() {
		Badges.validateDeathFromPoison();
		
		Dungeon.fail( getClass() );
		GLog.n( Messages.get(this, "ondeath") );
	}
}
