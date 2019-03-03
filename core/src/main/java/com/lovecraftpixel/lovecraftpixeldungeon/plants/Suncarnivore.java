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

import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.Statistics;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Barkskin;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Corruption;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Doom;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Healing;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.PinCushion;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Roots;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.SoulMark;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.HeroSubClass;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.EarthParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.ShaftParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.SuncarnivorePoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfCorruption;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
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
            Camera.main.shake( 1, 0.4f );
        }

        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.get( pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );
        }

        if(ch instanceof Mob){
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
			heroDanger = HeroDanger.MOBBENEFICIAL;
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