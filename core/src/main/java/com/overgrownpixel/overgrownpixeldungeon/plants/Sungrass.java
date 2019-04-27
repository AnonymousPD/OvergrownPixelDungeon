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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Corruption;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Doom;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FlavourBuff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.PinCushion;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SoulMark;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ShaftParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SungrassPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorruption;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;

public class Sungrass extends Plant {
	
	{
		image = 3;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch == Dungeon.hero) {
			if (Dungeon.hero.subClass == HeroSubClass.WARDEN) {
				Buff.affect(ch, Healing.class).setHeal(ch.HT, 0, 1);
			} else {
				Buff.affect(ch, Health.class).boost(ch.HT);
			}
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
    public void activate() {
        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.get( pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );
        }
        Plant.spawnLasher(pos);
    }

    @Override
    public void attackProc(Char enemy, int damage) {

    }

    @Override
    public void activatePosionMobBeneficial(Char attacker, Char defender) {
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

    public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_SUNGRASS;

			plantClass = Sungrass.class;
			heroDanger = HeroDanger.BENEFICIAL;

			bones = true;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SungrassPoisonParticle.FACTORY;
        }
	}
	
	public static class Health extends Buff {
		
		private static final float STEP = 1f;
		
		private int pos;
		private float partialHeal;
		private int level;

		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		@Override
		public boolean act() {
			if (target.pos != pos) {
				detach();
			}
			
			//for the hero, full heal takes ~50/93/111/120 turns at levels 1/10/20/30
			partialHeal += (40 + target.HT)/150f;
			
			if (partialHeal > 1){
				target.HP += (int)partialHeal;
				level -= (int)partialHeal;
				partialHeal -= (int)partialHeal;
				target.sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
				
				if (target.HP >= target.HT) {
					target.HP = target.HT;
					if (target instanceof Hero){
						((Hero)target).resting = false;
					}
				}
			}
			
			if (level <= 0) {
				detach();
			} else {
				BuffIndicator.refreshHero();
			}
			spend( STEP );
			return true;
		}

		public void boost( int amount ){
			level += amount;
			pos = target.pos;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.HERB_HEALING;
		}
		
		@Override
		public void tintIcon(Image icon) {
			FlavourBuff.greyIcon(icon, target.HT/4f, level);
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", level);
		}

		private static final String POS	= "pos";
		private static final String PARTIAL = "partial_heal";
		private static final String LEVEL = "level";

		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( POS, pos );
			bundle.put( PARTIAL, partialHeal);
			bundle.put( LEVEL, level);
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			pos = bundle.getInt( POS );
			partialHeal = bundle.getFloat( PARTIAL );
			level = bundle.getInt( LEVEL );

		}
	}
}
