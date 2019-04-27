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

import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.Statistics;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Barkskin;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Corruption;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Doom;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.PinCushion;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Roots;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SoulMark;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.EarthParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ShaftParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SuncarnivorePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorruption;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.particles.Emitter;

public class Suncarnivore extends Plant {

	{
		image = 38;
	}

	@Override
	public void activate( Char ch ) {

        if (ch == Dungeon.hero) {
            if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
                Buff.affect(ch, Barkskin.class).set((Dungeon.depth + 5)/2, 5);
                Buff.affect(ch, Healing.class).setHeal(ch.HT, 0, 1);
            } else {
                Buff.affect(ch, Earthroot.Armor.class).level(ch.HT);
                Buff.affect(ch, Sungrass.Health.class).boost(ch.HT);
            }
        }

        if(ch instanceof Mob){
            Buff.prolong( ch, Roots.class, 3 );
        }

        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.bottom( pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
            CellEmitter.get( pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );
            Camera.main.shake( 1, 0.4f );
        }

        if(ch instanceof Mob && !ch.properties().contains(Char.Property.INORGANIC)){
            if(ch.properties().contains(Char.Property.UNDEAD)){
                ch.damage(ch.HP, this);
            }
            if(ch.properties().contains(Char.Property.DEMONIC)){
                if(ch.buff(Corruption.class) != null || ch.buff(Doom.class) != null){
                    GLog.w( Messages.get(WandOfCorruption.class, "already_corrupted") );
                    return;
                }
                if (!ch.isImmune(Corruption.class)){
                    ch.HP = ch.HT;
                    for (Buff buff : ch.buffs()) {
                        if (buff.type == Buff.buffType.NEGATIVE
                                && !(buff instanceof SoulMark)) {
                            buff.detach();
                        } else if (buff instanceof PinCushion){
                            buff.detach();
                        }
                    }
                    Buff.affect(ch, Corruption.class);

                    Statistics.enemiesSlain++;
                    Badges.validateMonstersSlain();
                    Statistics.qualifiedForNoKilling = false;
                } else {
                    Buff.affect(ch, Doom.class);
                }
            }
            if(ch.properties().contains(Char.Property.FIERY) && ch.HT != ch.HP){
                ch.HP = ch.HT;
                ch.sprite.emitter().burst( Speck.factory( Speck.HEALING ), ch.HT-ch.HP );
            }
        }
	}

    @Override
    public void activate() {
        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.bottom( pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
            CellEmitter.get( pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );
            Camera.main.shake( 1, 0.4f );
        }
        Plant.spawnLasher(pos);
    }

    @Override
    public void attackProc(Char enemy, int damage) {

    }

    @Override
    public void activatePosionMobBeneficial(Char attacker, Char defender) {
        if(defender instanceof Mob){
            Buff.prolong( defender, Roots.class, 3 );
        }
        if(defender.properties().contains(Char.Property.UNDEAD)){
            defender.damage(defender.HP, this);
        } else if(defender.properties().contains(Char.Property.DEMONIC)){
            if(defender.buff(Corruption.class) != null || defender.buff(Doom.class) != null){
                GLog.w( Messages.get(WandOfCorruption.class, "already_corrupted") );
                return;
            }
            if (!defender.isImmune(Corruption.class)){
                defender.HP = defender.HT;
                for (Buff buff : defender.buffs()) {
                    if (buff.type == Buff.buffType.NEGATIVE
                            && !(buff instanceof SoulMark)) {
                        buff.detach();
                    } else if (buff instanceof PinCushion){
                        buff.detach();
                    }
                }
                Buff.affect(defender, Corruption.class);

                Statistics.enemiesSlain++;
                Badges.validateMonstersSlain();
                Statistics.qualifiedForNoKilling = false;
            } else {
                Buff.affect(defender, Doom.class);
            }
        } else {
            // 1 extra attack
            attacker.attack(defender);
        }
    }

	public static class Seed extends Plant.Seed{

		{
		    image = ItemSpriteSheet.SEED_SUNCARNIVORE;

			plantClass = Suncarnivore.class;
			heroDanger = HeroDanger.BENEFICIAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SuncarnivorePoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
