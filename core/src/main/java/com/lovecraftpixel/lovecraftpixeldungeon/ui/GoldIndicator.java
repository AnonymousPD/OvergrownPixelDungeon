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

package com.lovecraftpixel.lovecraftpixeldungeon.ui;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Game;
import com.watabou.noosa.ui.Component;

public class GoldIndicator extends Component {

	private static final float TIME	= 2f;
	
	private int lastValue = 0;
	
	private BitmapText tf;
	
	private float time;
	
	@Override
	protected void createChildren() {
		tf = new BitmapText( PixelScene.pixelFont);
		tf.hardlight( 0xFFFF00 );
		add( tf );
		
		visible = false;
	}
	
	@Override
	protected void layout() {
		tf.x = x + (width - tf.width()) / 2;
		tf.y = bottom() - tf.height();
	}
	
	@Override
	public void update() {
		super.update();
		
		if (visible) {
			
			time -= Game.elapsed;
			if (time > 0) {
				tf.alpha( time > TIME / 2 ? 1f : time * 2 / TIME );
			} else {
				visible = false;
			}
			
		}
		
		if (Dungeon.gold != lastValue) {
			
			lastValue = Dungeon.gold;
			
			tf.text( Integer.toString( lastValue ) );
			tf.measure();
			
			visible = true;
			time = TIME;
			
			layout();
		}
	}
}
