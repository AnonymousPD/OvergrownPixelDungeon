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

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Aids;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.BlackDeath;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Cholera;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Cordyceps;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Disease;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Ebola;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Herpes;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Influenza;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Leprosy;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Ligma;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Malaria;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Narcolepsy;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Polio;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.Rabies;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.SlowFever;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.SmallPox;
import com.overgrownpixel.overgrownpixeldungeon.actors.diseases.SpanishFlu;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SnowhedgePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Random;

public class Snowhedge extends Plant {

	{
		image = 19;
	}

	@Override
	public void activate( Char ch ) {

	    if(ch.properties().contains(Char.Property.INORGANIC)){
            return;
        }

        try {
            Disease d = (Disease) Random.element(diseases).newInstance();
            d.infect(ch);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
    }

	//TODO: update with new diseases
    public static final Class<?>[] diseases = {
            BlackDeath.class,
            Ebola.class,
            Aids.class,
            Cholera.class,
            Cordyceps.class,
            Herpes.class,
            Influenza.class,
            Leprosy.class,
            Ligma.class,
            Malaria.class,
            Narcolepsy.class,
            Polio.class,
            Rabies.class,
            SlowFever.class,
            SmallPox.class,
            SpanishFlu.class
    };

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_SNOWHEDGE;

			plantClass = Snowhedge.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SnowhedgePoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
