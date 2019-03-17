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

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Disease;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RedButton;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RenderedTextMultiline;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

public class WndInfoDisease extends Window {

	private static final float GAP	= 2;

	private static final int WIDTH = 120;

    private static final float BUTTON_HEIGHT	= 16;

	private SmartTexture icons;
	private TextureFilm film;

	public WndInfoDisease(final Disease disease){
		super();

		IconTitle titlebar = new IconTitle();

		icons = TextureCache.get( Assets.DISEASES_LARGE );
		film = new TextureFilm( icons, 16, 16 );

		Image diseaseIcon = new Image( icons );
		diseaseIcon.frame( film.get(disease.icon()) );
		disease.tintIcon(diseaseIcon);

		titlebar.icon( diseaseIcon );
		titlebar.label( Messages.titleCase(disease.toString()), Window.TITLE_COLOR );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );

		RenderedTextMultiline txtInfo = PixelScene.renderMultiline(disease.desc(), 6);
		txtInfo.maxWidth(WIDTH);
		txtInfo.setPos(titlebar.left(), titlebar.bottom() + GAP);
		add( txtInfo );

		if(Dungeon.hero.isAlive() && Dungeon.hero.researchedDiseases.contains(disease.getClass())){
            RenderedTextMultiline txtInfoSeeds = PixelScene.renderMultiline("\n\n"+Messages.get(Disease.class, "youneed", disease.cure.get(0).name(), disease.cure.get(1).name()), 6);
            txtInfoSeeds.maxWidth(WIDTH);
            txtInfoSeeds.setPos(titlebar.left(), txtInfo.bottom() + GAP);
            add( txtInfoSeeds );
            RedButton cure = new RedButton( Messages.get(Disease.class, "cure") ) {
                @Override
                protected void onClick() {
                    boolean first, second;
                    first = false;
                    second = false;
                    Plant.Seed seed1 = null;
                    Plant.Seed seed2 = null;
                    for (Item i : Dungeon.hero.belongings.backpack.items) {
                        if(disease.cure.get(0).isSimilar(i)){
                            first = true;
                            seed1 = (Plant.Seed) i;
                        }
                        if(disease.cure.get(1).isSimilar(i)){
                            second = true;
                            seed2 = (Plant.Seed) i;
                        }
                    }
                    if(first && second){
                        disease.detach();
                        seed1.detach(Dungeon.hero.belongings.backpack);
                        seed2.detach(Dungeon.hero.belongings.backpack);
                        GLog.p(Messages.get(Disease.class, "cureddisease"));
                    } else {
                        GLog.n(Messages.get(Disease.class, "nottherightseeds", disease.cure.get(0).name(), disease.cure.get(1).name()));
                    }
                    hide();
                }
            };
            cure.setRect( 0, txtInfoSeeds.bottom() + GAP, WIDTH, BUTTON_HEIGHT );
            add( cure );
            resize( WIDTH, (int)cure.bottom() );
        } else {
            resize( WIDTH, (int)(txtInfo.top() + txtInfo.height()) );
        }
	}
}
