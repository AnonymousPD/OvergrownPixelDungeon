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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LightParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.WraithSprite;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;

public class FriendlyWraith extends Wraith {

	private static final float SPAWN_DELAY	= 2f;
	
	private int level;
	
	{
		spriteClass = WraithSprite.class;
		
		HP = HT = 1;
		EXP = 0;
		
		flying = true;

		alignment = Alignment.ALLY;

		properties.add(Property.UNDEAD);
	}
	
	public static void spawnAround( int pos ) {
		for (int n : PathFinder.NEIGHBOURS4) {
			int cell = pos + n;
			if (Dungeon.level.passable[cell] && Actor.findChar( cell ) == null) {
				spawnAt( cell );
			}
		}
	}

	public static FriendlyWraith spawnAt(int pos ) {
		if (Dungeon.level.passable[pos] && Actor.findChar( pos ) == null) {
			
			FriendlyWraith w = new FriendlyWraith();
			w.adjustStats( Dungeon.depth );
			w.pos = pos;
			w.state = w.HUNTING;
			GameScene.add( w, SPAWN_DELAY );
			
			w.sprite.alpha( 0 );
			w.sprite.parent.add( new AlphaTweener( w.sprite, 1, 0.5f ) );
			w.sprite.color(0xFF90D3);
			
			w.sprite.emitter().burst( LightParticle.FACTORY, 5 );
			
			return w;
		} else {
		    if(!(Actor.findChar(pos) == null)){
		        if(Actor.findChar(pos) instanceof Hero){
		            //heros get healed by the spirit of the friendly wraith
                    GLog.p(Messages.get(FriendlyWraith.class, "heal"));
                    Actor.findChar(pos).HP = Actor.findChar(pos).HT;
                } else if(Actor.findChar(pos) instanceof Mob){
		            //mobs get hurt by this
                    Actor.findChar(pos).damage(new FriendlyWraith().damageRoll(), FriendlyWraith.class);
                }
            }
			return null;
		}
	}
}
