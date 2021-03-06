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

package com.overgrownpixel.overgrownpixeldungeon.sprites;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Eye;
import com.overgrownpixel.overgrownpixeldungeon.effects.Beam;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.particles.Emitter;

public class EyeSprite extends MobSprite {

	private int zapPos;

	private Animation charging;
	private Emitter chargeParticles;
	
	public EyeSprite() {
		super();
		
		texture( Assets.EYE );
		
		TextureFilm frames = new TextureFilm( texture, 16, 18 );
		
		idle = new Animation( 8, true );
		idle.frames( frames, 0, 1, 2 );

		charging = new Animation( 12, true);
		charging.frames( frames, 3, 4 );

		chargeParticles = centerEmitter();
		chargeParticles.autoKill = false;
		chargeParticles.pour(MagicMissile.MagicParticle.ATTRACTING, 0.05f);
		chargeParticles.on = false;
		
		run = new Animation( 12, true );
		run.frames( frames, 5, 6 );
		
		attack = new Animation( 8, false );
		attack.frames( frames, 4, 3 );
		zap = attack.clone();
		
		die = new Animation( 8, false );
		die.frames( frames, 7, 8, 9 );
		
		play( idle );
	}

	@Override
	public void link(Char ch) {
		super.link(ch);
		if (((Eye)ch).beamCharged) play(charging);
	}

	@Override
	public void update() {
		super.update();
		chargeParticles.pos(center());
		chargeParticles.visible = visible;
	}

	public void charge( int pos ){
		turnTo(ch.pos, pos);
		play(charging);
	}

	@Override
	public void play(Animation anim) {
		chargeParticles.on = anim == charging;
		super.play(anim);
	}

	@Override
	public void zap( int pos ) {
		zapPos = pos;
		super.zap( pos );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		super.onComplete( anim );
		
		if (anim == zap) {
			idle();
			if (Actor.findChar(zapPos) != null){
				parent.add(new Beam.DeathRay(center(), Actor.findChar(zapPos).sprite.center()));
			} else {
				parent.add(new Beam.DeathRay(center(), DungeonTilemap.raisedTileCenterToWorld(zapPos)));
			}
			((Eye)ch).deathGaze();
			ch.next();
		} else if (anim == die){
			chargeParticles.killAndErase();
		}
	}
}
