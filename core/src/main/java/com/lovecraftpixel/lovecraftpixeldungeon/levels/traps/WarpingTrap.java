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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Heap;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.BArray;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class WarpingTrap extends Trap {

	{
		color = TEAL;
		shape = STARS;
	}

	@Override
	public void activate() {
		CellEmitter.get(pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
		Sample.INSTANCE.play(Assets.SND_TELEPORT);
		
		Char ch = Actor.findChar(pos);
		if (ch != null && !ch.flying) {
			if (ch instanceof Hero) {
				ScrollOfTeleportation.teleportHero((Hero) ch);
				BArray.setFalse(Dungeon.level.visited);
				BArray.setFalse(Dungeon.level.mapped);
				GameScene.updateFog();
				Dungeon.observe();
				
			} else {
				int count = 10;
				int pos;
				do {
					pos = Dungeon.level.randomRespawnCell();
					if (count-- <= 0) {
						break;
					}
				} while (pos == -1);
				
				if (pos == -1 || Dungeon.bossLevel()) {
					
					GLog.w(Messages.get(ScrollOfTeleportation.class, "no_tele"));
					
				} else {
					
					ch.pos = pos;
					if (ch instanceof Mob && ((Mob) ch).state == ((Mob) ch).HUNTING) {
						((Mob) ch).state = ((Mob) ch).WANDERING;
					}
					ch.sprite.place(ch.pos);
					ch.sprite.visible = Dungeon.level.heroFOV[pos];
					
				}
			}
		}
		
		Heap heap = Dungeon.level.heaps.get(pos);
		
		if (heap != null){
			int cell = Dungeon.level.randomRespawnCell();
			
			Item item = heap.pickUp();
			
			if (cell != -1) {
				Dungeon.level.drop( item, cell );
			}
		}

	}
}
