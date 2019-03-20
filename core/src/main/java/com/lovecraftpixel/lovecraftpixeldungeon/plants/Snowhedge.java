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

import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Aids;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.BlackDeath;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Cholera;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Cordyceps;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Disease;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Ebola;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Herpes;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Influenza;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Leprosy;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Ligma;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Malaria;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Narcolepsy;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Polio;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.Rabies;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.SlowFever;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.SmallPox;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.diseases.SpanishFlu;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.SnowhedgePoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Random;

public class Snowhedge extends Plant {

	{
		image = 19;
	}

	@Override
	public void activate( Char ch ) {
        try {
            Disease d = (Disease) Random.element(diseases).newInstance();
            d.infect(ch);
        } catch (Exception e){
            LovecraftPixelDungeon.reportException(e);
        }
	}

	//TODO: update with new diseases
    private static final Class<?>[] diseases = {
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
