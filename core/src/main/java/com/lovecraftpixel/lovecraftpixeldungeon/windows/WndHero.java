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
import com.lovecraftpixel.lovecraftpixeldungeon.Statistics;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Disease;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.HeroSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BuffIndicator;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.DiseaseIndicator;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.ScrollPane;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.Locale;

public class WndHero extends WndTabbed {
	
	private static final int WIDTH		= 115;
	private static final int HEIGHT		= 100;
	
	private StatsTab stats;
	private BuffsTab buffs;
    private DiseaseTab diseases;
	
	private SmartTexture iconsb;
	private TextureFilm filmb;
    private SmartTexture iconsd;
    private TextureFilm filmd;
	
	public WndHero() {
		
		super();
		
		resize( WIDTH, HEIGHT );
		
		iconsb = TextureCache.get( Assets.BUFFS_LARGE );
		filmb = new TextureFilm(iconsb, 16, 16 );
        iconsd = TextureCache.get( Assets.DISEASES_LARGE );
        filmd = new TextureFilm(iconsd, 16, 16 );
		
		stats = new StatsTab();
		add( stats );
		
		buffs = new BuffsTab();
		add( buffs );
		buffs.setRect(0, 0, WIDTH, HEIGHT);
		buffs.setupList();

        diseases = new DiseaseTab();
        add( diseases );
        diseases.setRect(0, 0, WIDTH, HEIGHT);
        diseases.setupList();
		
		add( new LabeledTab( Messages.get(this, "stats") ) {
			protected void select( boolean value ) {
				super.select( value );
				stats.visible = stats.active = selected;
			};
		} );
		add( new LabeledTab( Messages.get(this, "buffs") ) {
			protected void select( boolean value ) {
				super.select( value );
				buffs.visible = buffs.active = selected;
			};
		} );
        add( new LabeledTab( Messages.get(this, "diseases") ) {
            protected void select( boolean value ) {
                super.select( value );
                diseases.visible = diseases.active = selected;
            };
        } );

		layoutTabs();
		
		select( 0 );
	}
	
	private class StatsTab extends Group {
		
		private static final int GAP = 5;
		
		private float pos;
		
		public StatsTab() {
			
			Hero hero = Dungeon.hero;

			IconTitle title = new IconTitle();
			title.icon( HeroSprite.avatar(hero.heroClass, hero.tier()) );
			if (hero.givenName().equals(hero.className()))
				title.label( Messages.get(this, "title", hero.lvl, hero.className() ).toUpperCase( Locale.ENGLISH ) );
			else
				title.label((hero.givenName() + "\n" + Messages.get(this, "title", hero.lvl, hero.className())).toUpperCase(Locale.ENGLISH));
			title.color(Window.SHPX_COLOR);
			title.setRect( 0, 0, WIDTH, 0 );
			add(title);

			pos = title.bottom() + 2*GAP;

			statSlot( Messages.get(this, "str"), hero.STR() );
			if (hero.shielding() > 0) statSlot( Messages.get(this, "health"), hero.HP + "+" + hero.shielding() + "/" + hero.HT );
			else statSlot( Messages.get(this, "health"), (hero.HP) + "/" + hero.HT );
			statSlot( Messages.get(this, "exp"), hero.exp + "/" + hero.maxExp() );

			pos += GAP;

			statSlot( Messages.get(this, "gold"), Statistics.goldCollected );
			statSlot( Messages.get(this, "depth"), Statistics.deepestFloor );

			pos += GAP;
		}

		private void statSlot( String label, String value ) {

			RenderedText txt = PixelScene.renderText( label, 8 );
			txt.y = pos;
			add( txt );

			txt = PixelScene.renderText( value, 8 );
			txt.x = WIDTH * 0.6f;
			txt.y = pos;
			PixelScene.align(txt);
			add( txt );
			
			pos += GAP + txt.baseLine();
		}
		
		private void statSlot( String label, int value ) {
			statSlot( label, Integer.toString( value ) );
		}
		
		public float height() {
			return pos;
		}
	}
	
	private class BuffsTab extends Component {
		
		private static final int GAP = 2;
		
		private float pos;
		private ScrollPane buffList;
		private ArrayList<BuffSlot> slots = new ArrayList<>();
		
		public BuffsTab() {
			buffList = new ScrollPane( new Component() ){
				@Override
				public void onClick( float x, float y ) {
					int size = slots.size();
					for (int i=0; i < size; i++) {
						if (slots.get( i ).onClick( x, y )) {
							break;
						}
					}
				}
			};
			add(buffList);
		}
		
		@Override
		protected void layout() {
			super.layout();
			buffList.setRect(0, 0, width, height);
		}
		
		private void setupList() {
			Component content = buffList.content();
			for (Buff buff : Dungeon.hero.buffs()) {
				if (buff.icon() != BuffIndicator.NONE) {
					BuffSlot slot = new BuffSlot(buff);
					slot.setRect(0, pos, WIDTH, slot.icon.height());
					content.add(slot);
					slots.add(slot);
					pos += GAP + slot.height();
				}
			}
			content.setSize(buffList.width(), pos);
			buffList.setSize(buffList.width(), buffList.height());
		}

		private class BuffSlot extends Component {

			private Buff buff;

			Image icon;
			RenderedText txt;

			public BuffSlot( Buff buff ){
				super();
				this.buff = buff;
				int index = buff.icon();

				icon = new Image(iconsb);
				icon.frame( filmb.get( index ) );
				buff.tintIcon(icon);
				icon.y = this.y;
				add( icon );

				txt = PixelScene.renderText( buff.toString(), 8 );
				txt.x = icon.width + GAP;
				txt.y = this.y + (int)(icon.height - txt.baseLine()) / 2;
				add( txt );

			}

			@Override
			protected void layout() {
				super.layout();
				icon.y = this.y;
				txt.x = icon.width + GAP;
				txt.y = pos + (int)(icon.height - txt.baseLine()) / 2;
			}
			
			protected boolean onClick ( float x, float y ) {
				if (inside( x, y )) {
					GameScene.show(new WndInfoBuff(buff));
					return true;
				} else {
					return false;
				}
			}
		}
	}

    private class DiseaseTab extends Component {

        private static final int GAP = 2;

        private float pos;
        private ScrollPane diseaseList;
        private ArrayList<DiseaseSlot> slots = new ArrayList<>();

        public DiseaseTab() {
            diseaseList = new ScrollPane( new Component() ){
                @Override
                public void onClick( float x, float y ) {
                    int size = slots.size();
                    for (int i=0; i < size; i++) {
                        if (slots.get( i ).onClick( x, y )) {
                            break;
                        }
                    }
                }
            };
            add(diseaseList);
        }

        @Override
        protected void layout() {
            super.layout();
            diseaseList.setRect(0, 0, width, height);
        }

        private void setupList() {
            Component content = diseaseList.content();
            for (Disease disease : Dungeon.hero.diseases()) {
                if (disease.icon() != DiseaseIndicator.NONE) {
                    DiseaseSlot slot = new DiseaseSlot(disease);
                    slot.setRect(0, pos, WIDTH, slot.icon.height());
                    content.add(slot);
                    slots.add(slot);
                    pos += GAP + slot.height();
                }
            }
            content.setSize(diseaseList.width(), pos);
            diseaseList.setSize(diseaseList.width(), diseaseList.height());
        }

        private class DiseaseSlot extends Component {

            private Disease disease;

            Image icon;
            RenderedText txt;

            public DiseaseSlot(Disease disease ){
                super();
                this.disease = disease;
                int index = disease.icon();

                icon = new Image(iconsd);
                icon.frame( filmd.get( index ) );
                disease.tintIcon(icon);
                icon.y = this.y;
                add( icon );

                txt = PixelScene.renderText( disease.toString(), 8 );
                txt.x = icon.width + GAP;
                txt.y = this.y + (int)(icon.height - txt.baseLine()) / 2;
                add( txt );

            }

            @Override
            protected void layout() {
                super.layout();
                icon.y = this.y;
                txt.x = icon.width + GAP;
                txt.y = pos + (int)(icon.height - txt.baseLine()) / 2;
            }

            protected boolean onClick ( float x, float y ) {
                if (inside( x, y )) {
                    GameScene.show(new WndInfoDisease(disease));
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
