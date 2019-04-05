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

package com.lovecraftpixel.lovecraftpixeldungeon.plants;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.BlackholeParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.BlackholeflowerPoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.features.Chasm;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.InterlevelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.particles.Emitter;

public class Blackholeflower extends Plant {

	{
		image = 25;
	}

	@Override
	public void activate( Char ch ) {

	    if(ch instanceof Hero){
            if (Dungeon.bossLevel() || Dungeon.depth <= 1) {

                GLog.w( Messages.get(ScrollOfTeleportation.class, "no_tele") );
                return;

            }

            Buff buff = Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class);
            if (buff != null) buff.detach();

            InterlevelScene.mode = InterlevelScene.Mode.RETURN;
            InterlevelScene.returnDepth = Dungeon.depth-1;
            InterlevelScene.returnPos = -1;
            Game.switchScene( InterlevelScene.class );
        }

        if(ch instanceof Mob){
            int quantity;
            if(!ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
                quantity = 3;
                if(ch.isAlive()) Chasm.mobFall((Mob) ch);
            } else {
               quantity = 1;
            }
            CellEmitter.center(ch.pos).burst( BlackholeParticle.FACTORY, quantity );
        }
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BLACKHOLEFLOWER;

			plantClass = Blackholeflower.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return BlackholeflowerPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
