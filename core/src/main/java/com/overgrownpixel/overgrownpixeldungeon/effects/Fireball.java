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
import com.watabou.glwrap.Blending;
import com.watabou.glwrap.Texture;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.ColorMath;
import com.watabou.utils.Random;
import com.watabou.utils.RectF;

public class Fireball extends Component {

	private static final RectF BLIGHT = new RectF( 0, 0, 0.25f, 1 );
	private static final RectF FLIGHT = new RectF( 0.25f, 0, 0.5f, 1 );
	private static final RectF FLAME1 = new RectF( 0.50f, 0, 0.75f, 1 );
	private static final RectF FLAME2 = new RectF( 0.75f, 0, 1.00f, 1 );
	
	private static final int COLOR = 0x28A900;
	
	private Image bLight;
	private Image fLight;
	private Group sparks;
	
	@Override
	protected void createChildren() {
		
		sparks = new Group();
		add( sparks );
		
		bLight = new Image( Assets.FIREBALL );
		bLight.frame( BLIGHT );
		bLight.origin.set( bLight.width / 2 );
		bLight.angularSpeed = -90;
		add( bLight );
		
		fLight = new Image( Assets.FIREBALL );
		fLight.frame( FLIGHT );
		fLight.origin.set( fLight.width / 2 );
		fLight.angularSpeed = 360;
		add( fLight );
		
		bLight.texture.filter( Texture.LINEAR, Texture.LINEAR );
	}
	
	@Override
	protected void layout() {
		
		bLight.x = x - bLight.width / 2;
		bLight.y = y - bLight.height / 2;
		
		fLight.x = x - fLight.width / 2;
		fLight.y = y - fLight.height / 2;
	}
	
	@Override
	public void update() {
		
		super.update();
		
		if (Random.Float() < Game.elapsed) {
			PixelParticle spark = (PixelParticle)sparks.recycle( PixelParticle.Shrinking.class );
			spark.reset( x, y, ColorMath.random( COLOR, 0x66FF66 ), 2, Random.Float( 0.5f, 1.0f ) );
			spark.speed.set(
				Random.Float( -40, +40 ),
				Random.Float( -60, +20 ) );
			spark.acc.set( 0, +80 );
			sparks.add( spark );
		}
	}
	
	@Override
	public void draw() {
		Blending.setLightMode();
		super.draw();
		Blending.setNormalMode();
	}
}
