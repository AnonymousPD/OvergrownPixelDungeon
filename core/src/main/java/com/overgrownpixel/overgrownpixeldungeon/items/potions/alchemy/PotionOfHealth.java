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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Bleeding;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Infested;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Midas;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Poison;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Apricobush;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class PotionOfHealth extends Potion {

	{
		initials = 22;
	}

    @Override
    public void apply(Hero hero) {
	    setKnown();
        (hero.buff( Hunger.class )).satisfy( Hunger.HUNGRY/2f );
        GLog.p(Messages.get(Apricobush.class, "hunger"));
        hero.HP = hero.HT++;
        cure(hero);
    }

    public static void cure( Char ch ) {
        Buff.detach( ch, Poison.class );
        Buff.detach( ch, Cripple.class );
        Buff.detach( ch, Weakness.class );
        Buff.detach( ch, Bleeding.class );
        Buff.detach( ch, Infested.class );
        Buff.detach( ch, Midas.class );
        if(Random.Boolean()){
            Disease.detach(ch, Aids.class);
            Disease.detach(ch, BlackDeath.class);
            Disease.detach(ch, Cholera.class);
            Disease.detach(ch, Cordyceps.class);
            Disease.detach(ch, Ebola.class);
            Disease.detach(ch, Herpes.class);
            Disease.detach(ch, Influenza.class);
            Disease.detach(ch, Leprosy.class);
            Disease.detach(ch, Ligma.class);
            Disease.detach(ch, Malaria.class);
            Disease.detach(ch, Narcolepsy.class);
            Disease.detach(ch, Polio.class);
            Disease.detach(ch, Rabies.class);
            Disease.detach(ch, SlowFever.class);
            Disease.detach(ch, SmallPox.class);
            Disease.detach(ch, SpanishFlu.class);
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
