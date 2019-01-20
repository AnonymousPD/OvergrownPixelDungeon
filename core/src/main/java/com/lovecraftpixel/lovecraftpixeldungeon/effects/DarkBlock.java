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

package com.lovecraftpixel.lovecraftpixeldungeon.effects;

import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.Gizmo;

public class DarkBlock extends Gizmo{

	private CharSprite target;

	public DarkBlock( CharSprite target ) {
		super();

		this.target = target;
	}

	@Override
	public void update() {
		super.update();

		target.brightness(0.4f);

	}

	public void lighten() {

		target.resetColor();
		killAndErase();

	}

	public static DarkBlock darken( CharSprite sprite ) {

		DarkBlock darkBlock = new DarkBlock( sprite );
		if (sprite.parent != null)
			sprite.parent.add( darkBlock );

		return darkBlock;
	}

}
