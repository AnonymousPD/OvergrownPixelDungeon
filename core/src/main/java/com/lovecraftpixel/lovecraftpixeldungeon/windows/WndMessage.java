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

package com.lovecraftpixel.lovecraftpixeldungeon.windows;

import com.lovecraftpixel.lovecraftpixeldungeon.LPDSettings;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RenderedTextMultiline;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;

public class WndMessage extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 144;
	private static final int MARGIN = 4;
	
	public WndMessage( String text ) {
		
		super();
		
		RenderedTextMultiline info = PixelScene.renderMultiline( text, 6 );
		info.maxWidth((LPDSettings.landscape() ? WIDTH_L : WIDTH_P) - MARGIN * 2);
		info.setPos(MARGIN, MARGIN);
		add( info );

		resize(
			(int)info.width() + MARGIN * 2,
			(int)info.height() + MARGIN * 2 );
	}
}
