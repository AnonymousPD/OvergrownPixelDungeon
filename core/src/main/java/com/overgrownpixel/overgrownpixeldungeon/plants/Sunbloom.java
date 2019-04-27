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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SunbloomPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;

public class Sunbloom extends Plant {

	{
		image = 20;
	}

	@Override
	public void activate( Char ch ) {

	    if(Dungeon.level.heroFOV[pos]){
            GameScene.flash( 0xFFFFFF );
            Sample.INSTANCE.play( Assets.SND_BLAST );
            Invisibility.dispel();
        }

        //scales from 0x to 1x power, maxing at ~10% HP
        float hpPercent = (ch.HT - ch.HP)/(float)(ch.HT);
        float power = Math.min( 4f, 4.45f*hpPercent);

        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (Dungeon.level.heroFOV[mob.pos]) {
                //deals 10%HT, plus 0-90%HP based on scaling
                mob.damage(Math.round(mob.HT/10f + (mob.HP * power * 0.225f)), this);
                if (mob.isAlive()) {
                    Buff.prolong(mob, Blindness.class, Math.round(6 + power));
                }
            }
        }

        if(!(ch instanceof Hero) & Dungeon.level.heroFOV[ch.pos]){
            Buff.prolong(Dungeon.hero, Weakness.class, Weakness.DURATION/2f);
            Buff.prolong(Dungeon.hero, Blindness.class, Math.round(6 + power));
        }

        if(ch.properties().contains(Char.Property.INORGANIC)){
            return;
        }

        Buff.prolong(ch, Weakness.class, Weakness.DURATION/2f);
        Buff.prolong(ch, Blindness.class, Math.round(6 + power));

        Dungeon.observe();
	}

    @Override
    public void activate() {
        if(Dungeon.level.heroFOV[pos]){
            GameScene.flash( 0xFFFFFF );
            Sample.INSTANCE.play( Assets.SND_BLAST );
            Invisibility.dispel();
        }
        Plant.spawnLasher(pos);
    }

    @Override
    public void attackProc(Char enemy, int damage) {

    }

    @Override
    public void activatePosionDangerous(Char attacker, Char defender) {
	    float hpPercent = (defender.HT - defender.HP)/(float)(defender.HT);
        float power = Math.min( 4f, 4.45f*hpPercent);

        Buff.prolong(defender, Weakness.class, Weakness.DURATION/2f);
        Buff.prolong(defender, Blindness.class, Math.round(6 + power));
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_SUNBLOOM;

			plantClass = Sunbloom.class;
			heroDanger = HeroDanger.DANGEROUS;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SunbloomPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
