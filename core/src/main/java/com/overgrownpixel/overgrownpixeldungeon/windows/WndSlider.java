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

package com.overgrownpixel.overgrownpixeldungeon.windows;

import com.overgrownpixel.overgrownpixeldungeon.LPDSettings;
import com.overgrownpixel.overgrownpixeldungeon.scenes.PixelScene;
import com.overgrownpixel.overgrownpixeldungeon.ui.OptionSlider;
import com.overgrownpixel.overgrownpixeldungeon.ui.RenderedTextMultiline;
import com.overgrownpixel.overgrownpixeldungeon.ui.Window;

public class WndSlider extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 144;

	private static final int MARGIN 		= 2;

    private static final int SLIDER_HEIGHT	= 24;

	public WndSlider(String title, String message, String sliderTitle, String minText, String maxText, int minValue, int maxValue, int defaultValue) {
		super();

		int width = LPDSettings.landscape() ? WIDTH_L : WIDTH_P;

		RenderedTextMultiline tfTitle = PixelScene.renderMultiline( title, 9 );
		tfTitle.hardlight( TITLE_COLOR );
		tfTitle.setPos(MARGIN, MARGIN);
		tfTitle.maxWidth(width - MARGIN * 2);
		add( tfTitle );
		
		RenderedTextMultiline tfMesage = PixelScene.renderMultiline( 6 );
		tfMesage.text(message, width - MARGIN * 2);
		tfMesage.setPos( MARGIN, tfTitle.top() + tfTitle.height() + MARGIN );
		add( tfMesage );
		
		float pos = tfMesage.bottom() + MARGIN;

        OptionSlider scale = new OptionSlider(sliderTitle,
                minText,
                maxText,
                minValue,
                maxValue) {
            @Override
            protected void onChange() {
                onSliderMoved(getSelectedValue(), this);
                setSelectedValue(getSelectedValue());
            }
        };
        scale.setSelectedValue(defaultValue);
        scale.setRect(0, pos, width, SLIDER_HEIGHT);
        add(scale);

        pos = scale.bottom();
		
		resize( width, (int)pos );
	}

	protected void onSliderMoved(int selectedValue, OptionSlider slider){
	    slider.setSelectedValue(selectedValue);
    }
}
