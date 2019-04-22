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

import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Barrier;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfShielding;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class ElixirOfVitality extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_SURGE;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect( hero, Healing.class ).setHeal((int)(0.8f*hero.HT + 14), 0.25f, 0);
		PotionOfHealing.cure(hero);
		Buff.affect(hero, Barrier.class).setShield((int)(0.6f*hero.HT + 10));
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (30 + 50);
	}
	
	public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfHealing.class, PotionOfShielding.class};
			inQuantity = new int[]{1, 1};
			
			cost = 2;
			
			output = ElixirOfVitality.class;
			outQuantity = 1;
		}
		
	}
	
}
