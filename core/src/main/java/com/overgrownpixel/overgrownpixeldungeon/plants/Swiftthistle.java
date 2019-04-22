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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FlavourBuff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Haste;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Slow;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SwiftthistlePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;

public class Swiftthistle extends Plant {
	
	{
		image = 2;
	}
	
	@Override
	public void activate( Char ch ) {
		if (ch == Dungeon.hero) {
			Buff.affect(ch, TimeBubble.class).reset();
			if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
				Buff.affect(ch, Haste.class, 5f);
			}
		}
		if(ch instanceof Mob){

		    if(ch.properties().contains(Char.Property.INORGANIC)){
                return;
            }

            Buff.prolong( ch, Haste.class, Haste.DURATION);
        }
	}

    @Override
    public void activate() {
        Plant.spawnLasher(pos);
    }

    @Override
    public void activatePosionMobBeneficial(Char attacker, Char defender) {
        Buff.prolong( defender, Slow.class, Slow.DURATION );
    }

    public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_SWIFTTHISTLE;
			
			plantClass = Swiftthistle.class;
			heroDanger = HeroDanger.BENEFICIAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SwiftthistlePoisonParticle.FACTORY;
        }
	}
	
	public static class TimeBubble extends Buff {
		
		private float left;
		private int pos;
		
		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.SLOW;
		}
		
		@Override
		public void tintIcon(Image icon) {
			if (left < 4) FlavourBuff.greyIcon(icon, 4f, left);
		}
		
		public void reset(){
			pos = target.pos;
			left = 6f;
		}
		
		public void processTime( float time ){
			if (target.pos != pos){
				left = 0f;
			}
			
			left -= time;
			BuffIndicator.refreshHero();
			
			if (left <= 0){
				detach();
			}
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", dispTurns(left));
		}
		
		private static final String POS	= "pos";
		private static final String LEFT = "left";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put( POS, pos );
			bundle.put( LEFT, left );
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			pos = bundle.getInt( POS );
			left = bundle.getInt( LEFT );
		}
	}
}
