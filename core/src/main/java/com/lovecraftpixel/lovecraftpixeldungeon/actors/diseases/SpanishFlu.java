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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases;

import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Dreamfoil;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Firebloom;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.DiseaseIndicator;

public class SpanishFlu extends FlavourDisease {

    {
        DURATION = 1000f;
        cure.add(new Dreamfoil.Seed());
        cure.add(new Firebloom.Seed());
    }

    @Override
	public int icon() {
		return DiseaseIndicator.SPANISH_FLU;
	}

    @Override
	public String toString() {
		return Messages.get(this, "name");
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(cooldown()+1));
	}
}
