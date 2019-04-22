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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Flare;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfDetectCurse;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class HolyBlast extends Spell {
	
	{
		image = ItemSpriteSheet.HOLYBLAST;
	}

    @Override
    protected void onCast(Hero hero) {
        new Flare( 12, 32 ).show( curUser.sprite, 2f ) ;
        Weakness.detach( hero, Weakness.class );
        Invisibility.dispel();
        for(Item item : Dungeon.hero.belongings){
            if(ScrollOfRemoveCurse.uncursable(item)){
                ScrollOfRemoveCurse.uncurse(hero, item);
            }
        }
        detach( curUser.belongings.backpack );
        updateQuickslot();
    }

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 10) / 4f));
	}

    public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfRemoveCurse.class, StoneOfDetectCurse.class};
			inQuantity = new int[]{1, 1};
			
			cost = 2;
			
			output = HolyBlast.class;
			outQuantity = 4;
		}
		
	}
}
