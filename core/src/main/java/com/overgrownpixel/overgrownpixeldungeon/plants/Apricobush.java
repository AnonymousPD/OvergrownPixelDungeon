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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ApricobushPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.particles.Emitter;

public class Apricobush extends Plant {

	{
		image = 15;
	}

	@Override
	public void activate( Char ch ) {
	    if(ch.properties().contains(Char.Property.INORGANIC)){
	        return;
        }
	    if(ch instanceof Hero){
            (ch.buff( Hunger.class )).satisfy( Hunger.HUNGRY/2f );
            GLog.p(Messages.get(this, "hunger"));
        }
        if(ch.isAlive()){
            ch.HP *= 3;
            if(ch.HP >= ch.HT){
                ch.HP = ch.HT;
            }
        }
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
    }

    @Override
    public void activatePosionMobBeneficial(Char attacker, Char defender) {
        defender.HT--;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_APRICOBUSH;

			plantClass = Apricobush.class;
			heroDanger = HeroDanger.BENEFICIAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return ApricobushPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
