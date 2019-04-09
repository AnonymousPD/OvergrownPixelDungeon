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

package com.lovecraftpixel.lovecraftpixeldungeon.levels.plates;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public abstract class PressurePlate implements Bundlable {

	public String name = Messages.get(this, "name");

	public int pos;

	public boolean active = false;
	public float weightRequired = 0f;

	public PressurePlate set(int pos, float weightRequired){
		this.pos = pos;
		this.weightRequired = weightRequired;
		return this;
	}

	public void checkAndTrigger(float weight) {
		if (!active && weight >= weightRequired) {
			if (Dungeon.level.heroFOV[pos]) {
				Sample.INSTANCE.play(Assets.SND_TRAP);
			}
			active = true;
			activate();
		}
        GameScene.updateMap(pos);
	}


    public abstract void deactivate();
	public abstract void activate();

	private static final String POS	= "pos";
	private static final String ACTIVE = "active";
    private static final String WEIGHT = "weight";

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		pos = bundle.getInt( POS );
		weightRequired = bundle.getFloat(WEIGHT);
		if (bundle.contains( ACTIVE )){
			active = bundle.getBoolean( ACTIVE );
		}
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( POS, pos );
		bundle.put( ACTIVE, active );
		bundle.put( WEIGHT, weightRequired );
	}

	public String desc() {
		return Messages.get(this, "desc");
	}
}
