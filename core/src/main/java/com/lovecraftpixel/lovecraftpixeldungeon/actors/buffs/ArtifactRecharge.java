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

import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Belongings;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.Artifact;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

//TODO this may be very powerful, consider balancing
public class ArtifactRecharge extends Buff {
	
	{
		type = buffType.POSITIVE;
	}
	
	private int left;
	
	@Override
	public boolean act() {
		
		if (target instanceof Hero){
			Belongings b = ((Hero) target).belongings;
			
			if (b.misc1 instanceof Artifact){
				((Artifact)b.misc1).charge((Hero)target);
			}
			if (b.misc2 instanceof Artifact){
				((Artifact)b.misc2).charge((Hero)target);
			}
		}
		
		left--;
		if (left <= 0){
			detach();
		} else {
			spend(TICK);
		}
		
		return true;
	}
	
	public void set( int amount ){
		left = amount;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.RECHARGING;
	}
	
	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(0, 1f, 0);
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(left+1));
	}
	
	private static final String LEFT = "left";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( LEFT, left );
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		left = bundle.getInt(LEFT);
	}
}
