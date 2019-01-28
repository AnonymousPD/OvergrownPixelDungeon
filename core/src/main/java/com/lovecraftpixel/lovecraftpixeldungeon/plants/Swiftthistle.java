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

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.FlavourBuff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Haste;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.HeroSubClass;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.SwiftthistlePoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BuffIndicator;
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
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_SWIFTTHISTLE;
			
			plantClass = Swiftthistle.class;
		}

        @Override
        public void onProc(Char attacker, Char defender, int damage) {

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
