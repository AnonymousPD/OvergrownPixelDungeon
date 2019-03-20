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

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Disease;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.windows.WndInfoDisease;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Button;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DiseaseIndicator extends Component {

	//transparent icon
	public static final int NONE	= 63;

	//FIXME this is becoming a mess, should do a big cleaning pass on all of these
	//and think about tinting options
	public static final int BLACK_DEATH	= 0;
    public static final int EBOLA	    = 1;
    public static final int POLIO	    = 2;
    public static final int SMALL_POX	= 3;
    public static final int CHOLERA	    = 4;
    public static final int MALARIA	    = 5;
    public static final int SPANISH_FLU	= 6;
    public static final int INFLUENZA	= 7;
    public static final int AIDS	    = 8;
    public static final int CORDYCEPS	= 9;
    public static final int HERPES	    = 10;
    public static final int RABIES	    = 11;
    public static final int SLOW_FEVER	= 12;
    public static final int NECROSIS	= 13;
    public static final int LEPROSY	    = 14;
    public static final int LIGMA	    = 15;

	public static final int SIZE	= 7;

	private static DiseaseIndicator heroInstance;

	private SmartTexture texture;
	private TextureFilm film;

	private LinkedHashMap<Disease, DiseaseIcon> diseaseIcons = new LinkedHashMap<>();
	private boolean needsRefresh;
	private Char ch;

	public DiseaseIndicator(Char ch ) {
		super();
		
		this.ch = ch;
		if (ch == Dungeon.hero) {
			heroInstance = this;
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		if (this == heroInstance) {
			heroInstance = null;
		}
	}
	
	@Override
	protected void createChildren() {
		texture = TextureCache.get( Assets.DISEASES_SMALL );
		film = new TextureFilm( texture, SIZE, SIZE );
	}
	
	@Override
	public synchronized void update() {
		super.update();
		if (needsRefresh){
			needsRefresh = false;
			layout();
		}
	}
	
	@Override
	protected void layout() {
		
		ArrayList<Disease> newDisease = new ArrayList<>();
		for (Disease disease : ch.diseases()) {
			if (disease.icon() != NONE) {
				newDisease.add(disease);
			}
		}
		
		//remove any icons no longer present
		for (Disease disease : diseaseIcons.keySet().toArray(new Disease[0])){
			if (!newDisease.contains(disease)){
				Image icon = diseaseIcons.get( disease ).icon;
				icon.origin.set( SIZE / 2 );
				add( icon );
				add( new AlphaTweener( icon, 0, 0.6f ) {
					@Override
					protected void updateValues( float progress ) {
						super.updateValues( progress );
						image.scale.set( 1 + 5 * progress );
					}
					
					@Override
					protected void onComplete() {
						image.killAndErase();
					}
				} );
				
				diseaseIcons.get( disease ).destroy();
				remove(diseaseIcons.get( disease ));
				diseaseIcons.remove( disease );
			}
		}
		
		//add new icons
		for (Disease disease : newDisease) {
			if (!diseaseIcons.containsKey(disease)) {
				DiseaseIcon icon = new DiseaseIcon( disease );
				add(icon);
				diseaseIcons.put( disease, icon );
			}
		}
		
		//layout
		int pos = 0;
		for (DiseaseIcon icon : diseaseIcons.values()){
			icon.updateIcon();
			icon.setRect(x + pos * (SIZE + 2), y, 9, 12);
			pos++;
		}
	}

	private class DiseaseIcon extends Button {

		private Disease disease;

		public Image icon;

		public DiseaseIcon(Disease disease ){
			super();
			this.disease = disease;

			icon = new Image( texture );
			icon.frame( film.get( disease.icon() ) );
			add( icon );
		}
		
		public void updateIcon(){
			icon.frame( film.get( disease.icon() ) );
            disease.tintIcon(icon);
		}

		@Override
		protected void layout() {
			super.layout();
			icon.x = this.x+1;
			icon.y = this.y+2;
		}

		@Override
		protected void onClick() {
			if (disease.icon() != NONE)
				GameScene.show(new WndInfoDisease(disease));
		}
	}
	
	public static void refreshHero() {
		if (heroInstance != null) {
			heroInstance.needsRefresh = true;
		}
	}
}
