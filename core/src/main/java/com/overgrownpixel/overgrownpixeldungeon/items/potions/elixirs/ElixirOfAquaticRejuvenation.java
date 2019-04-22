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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FlavourBuff;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.quest.GooBlob;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class ElixirOfAquaticRejuvenation extends Elixir {
	
	{
		//TODO finish visuals
		image = ItemSpriteSheet.ELIXIR_AQUA;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect(hero, AquaHealing.class).set(hero.HT * 3);
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (30 + 50);
	}
	
	public static class AquaHealing extends Buff {
		
		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		private int left;
		
		public void set( int amount ){
			if (amount > left) left = amount;
		}
		
		@Override
		public boolean act() {
			
			if (Dungeon.level.water[target.pos] && target.HP < target.HT){
				target.HP++;
				target.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			}
			
			if (left-- <= 0){
				detach();
			} else {
				spend(TICK);
				if (left <= target.HT/4f){
					BuffIndicator.refreshHero();
				}
			}
			return true;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.HEALING;
		}
		
		@Override
		public void tintIcon(Image icon) {
			FlavourBuff.greyIcon(icon, target.HT/4f, left);
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", left+1);
		}
		
		private static final String LEFT = "left";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( LEFT, left );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			left = bundle.getInt( LEFT );
			
		}
	}
	
	public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfHealing.class, GooBlob.class};
			inQuantity = new int[]{1, 1};
			
			cost = 4;
			
			output = ElixirOfAquaticRejuvenation.class;
			outQuantity = 1;
		}
		
	}
	
}
