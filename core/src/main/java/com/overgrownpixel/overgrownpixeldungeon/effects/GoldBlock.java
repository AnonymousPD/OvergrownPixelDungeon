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

package com.overgrownpixel.overgrownpixeldungeon.effects;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.audio.Sample;

public class GoldBlock extends Gizmo {

	private CharSprite target;

	public GoldBlock(CharSprite target ) {
		super();

		this.target = target;
	}
	
	@Override
	public void update() {
		super.update();

        target.tint(0xCCC300,1.0f );
	}
	
	public void cure() {

		target.resetColor();
		killAndErase();

		if (visible) {
			Sample.INSTANCE.play( Assets.SND_MELD );
		}
	}
	
	public static GoldBlock infuse(CharSprite sprite ) {
		
		GoldBlock goldBlock = new GoldBlock( sprite );
		if (sprite.parent != null)
			sprite.parent.add( goldBlock );
		
		return goldBlock;
	}
}
