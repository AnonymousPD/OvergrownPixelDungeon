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

package com.lovecraftpixel.lovecraftpixeldungeon.levels.traps;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.missiles.darts.Dart;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.MissileSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class WornDartTrap extends Trap {

	{
		color = GREY;
		shape = CROSSHAIR;
	}

	@Override
	public Trap hide() {
		//this one can't be hidden
		return reveal();
	}

	@Override
	public void activate() {
		Char target = Actor.findChar(pos);
		
		//find the closest char that can be aimed at
		if (target == null){
			for (Char ch : Actor.chars()){
				Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
				if (bolt.collisionPos == ch.pos &&
						(target == null || Dungeon.level.trueDistance(pos, ch.pos) < Dungeon.level.trueDistance(pos, target.pos))){
					target = ch;
				}
			}
		}
		if (target != null) {
			final Char finalTarget = target;
			final WornDartTrap trap = this;
			if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
				Actor.add(new Actor() {
					
					{
						//it's a visual effect, gets priority no matter what
						actPriority = VFX_PRIO;
					}
					
					@Override
					protected boolean act() {
						final Actor toRemove = this;
						((MissileSprite) LovecraftPixelDungeon.scene().recycle(MissileSprite.class)).
							reset(pos, finalTarget.sprite, new Dart(), new Callback() {
								@Override
								public void call() {
								int dmg = Random.NormalIntRange(1, 4) - finalTarget.drRoll();
								finalTarget.damage(dmg, trap);
								if (finalTarget == Dungeon.hero && !finalTarget.isAlive()){
									Dungeon.fail( trap.getClass()  );
								}
								Sample.INSTANCE.play(Assets.SND_HIT, 1, 1, Random.Float(0.8f, 1.25f));
								finalTarget.sprite.bloodBurstA(finalTarget.sprite.center(), dmg);
								finalTarget.sprite.flash();
								Actor.remove(toRemove);
								next();
								}
							});
						return false;
					}
				});
			} else {
				finalTarget.damage(Random.NormalIntRange(1, 4) - finalTarget.drRoll(), trap);
			}
		}
	}
}
