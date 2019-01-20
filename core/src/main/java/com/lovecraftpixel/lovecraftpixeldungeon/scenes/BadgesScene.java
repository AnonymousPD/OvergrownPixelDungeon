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

package com.lovecraftpixel.lovecraftpixeldungeon.scenes;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.LPDSettings;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.BadgeBanner;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Archs;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.ExitButton;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndBadge;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Button;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.List;

public class BadgesScene extends PixelScene {

	@Override
	public void create() {

		super.create();

		Music.INSTANCE.play( Assets.THEME, true );

		uiCamera.visible = false;

		int w = Camera.main.width;
		int h = Camera.main.height;

		Archs archs = new Archs();
		archs.setSize( w, h );
		add( archs );

		float left = 5;
		float top = 16;

		RenderedText title = PixelScene.renderText( Messages.get(this, "title"), 9 );
		title.hardlight(Window.TITLE_COLOR);
		title.x = (w - title.width()) / 2f;
		title.y = (top - title.baseLine()) / 2f;
		align(title);
		add(title);

		Badges.loadGlobal();

		List<Badges.Badge> badges = Badges.filtered( true );

		int blankBadges = 36;
		blankBadges -= badges.size();
		if (badges.contains(Badges.Badge.ALL_ITEMS_IDENTIFIED))	blankBadges -= 6;
		if (badges.contains(Badges.Badge.YASD)) 				blankBadges -= 5;
		blankBadges = Math.max(0, blankBadges);

		//guarantees a max of 5 rows in landscape, and 8 in portrait, assuming a max of 40 buttons
		int nCols = LPDSettings.landscape() ? 7 : 4;
		if (badges.size() + blankBadges > 32 && !LPDSettings.landscape())	nCols++;

		int nRows = 1 + (blankBadges + badges.size())/nCols;

		float badgeWidth = (w - 2*left)/nCols;
		float badgeHeight = (h - 2*top)/nRows;

		for (int i = 0; i < badges.size() + blankBadges; i++){
			int row = i / nCols;
			int col = i % nCols;
			Badges.Badge b = i < badges.size() ? badges.get( i ) : null;
			BadgeButton button = new BadgeButton( b );
			button.setPos(
					left + col * badgeWidth + (badgeWidth - button.width()) / 2,
					top + row * badgeHeight + (badgeHeight - button.height()) / 2);
			align(button);
			add( button );
		}

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		fadeIn();

		Badges.loadingListener = new Callback() {
			@Override
			public void call() {
				if (Game.scene() == BadgesScene.this) {
					LovecraftPixelDungeon.switchNoFade( BadgesScene.class );
				}
			}
		};
	}

	@Override
	public void destroy() {

		Badges.saveGlobal();
		Badges.loadingListener = null;

		super.destroy();
	}

	@Override
	protected void onBackPressed() {
		LovecraftPixelDungeon.switchNoFade( TitleScene.class );
	}

	private static class BadgeButton extends Button {

		private Badges.Badge badge;

		private Image icon;

		public BadgeButton( Badges.Badge badge ) {
			super();

			this.badge = badge;
			active = (badge != null);

			icon = active ? BadgeBanner.image(badge.image) : new Image( Assets.LOCKED );
			add(icon);

			setSize( icon.width(), icon.height() );
		}

		@Override
		protected void layout() {
			super.layout();

			icon.x = x + (width - icon.width()) / 2;
			icon.y = y + (height - icon.height()) / 2;
		}

		@Override
		public void update() {
			super.update();

			if (Random.Float() < Game.elapsed * 0.1) {
				BadgeBanner.highlight( icon, badge.image );
			}
		}

		@Override
		protected void onClick() {
			Sample.INSTANCE.play( Assets.SND_CLICK, 0.7f, 0.7f, 1.2f );
			Game.scene().add( new WndBadge( badge ) );
		}
	}
}
