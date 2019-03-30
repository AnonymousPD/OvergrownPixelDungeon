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

package com.lovecraftpixel.lovecraftpixeldungeon.items.potions.alchemy;

import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Bleeding;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Cripple;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Healing;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Infested;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Midas;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Poison;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Weakness;
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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.Potion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfHealing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfStrength;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;

public class PotionOfPower extends Potion {

	{
		initials = 33;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();

        hero.STR++;
        hero.sprite.showStatus( CharSprite.POSITIVE, Messages.get(PotionOfStrength.class, "msg_1") );
        GLog.p( Messages.get(PotionOfStrength.class, "msg_2") );

        Badges.validateStrengthAttained();

        Buff.affect( hero, Healing.class ).setHeal((int)(0.8f*hero.HT + 14), 0.25f, 0);
        cure( hero );
        GLog.p( Messages.get(PotionOfHealing.class, "heal") );
    }

    public static void cure( Char ch ) {
        Buff.detach( ch, Poison.class );
        Buff.detach( ch, Cripple.class );
        Buff.detach( ch, Weakness.class );
        Buff.detach( ch, Bleeding.class );
        Buff.detach( ch, Infested.class );
        Buff.detach( ch, Midas.class );
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

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
