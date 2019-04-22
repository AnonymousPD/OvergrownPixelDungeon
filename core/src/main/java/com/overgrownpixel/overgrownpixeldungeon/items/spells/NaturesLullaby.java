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

package com.overgrownpixel.overgrownpixeldungeon.items.spells;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.livingplants.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.effects.Flare;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class NaturesLullaby extends Spell {
	
	{
		image = ItemSpriteSheet.NATURES_LULLABY;
	}

    @Override
    protected void onCast(Hero hero) {
        new Flare(8, 14).color(0x1CB82E,true).show(hero.sprite, 0.5f);
        Invisibility.dispel();
        GLog.h(Messages.get(this, "sleep"));
        for(Mob mob : Dungeon.level.mobs){
            if(mob instanceof LivingPlant){
                if(Dungeon.level.heroFOV[mob.pos]){
                    ((LivingPlant) mob).becomePlant = true;
                }
            }
        }
        detach( curUser.belongings.backpack );
        updateQuickslot();

    }

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 50) / 6f));
	}

    public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfLullaby.class, PotionOfRegrowth.class};
			inQuantity = new int[]{1, 1};
			
			cost = 3;
			
			output = NaturesLullaby.class;
			outQuantity = 6;
		}
		
	}
}
