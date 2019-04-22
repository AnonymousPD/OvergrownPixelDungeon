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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.alchemy.PotionOfPlants;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;

public class SeasonChange extends Spell {
	
	{
		image = ItemSpriteSheet.SEASONCHANGE;
	}

    @Override
    protected void onCast(Hero hero) {
        for(Plant plant : Dungeon.level.plants.values()){
            Dungeon.level.uproot( plant.pos );
            if (Dungeon.level.heroFOV[plant.pos]) {
                CellEmitter.get( plant.pos ).burst( LeafParticle.GENERAL, 6 );
            }
            Dungeon.level.drop(Plant.getPlant(plant), plant.pos);
        }
        detach( curUser.belongings.backpack );
        updateQuickslot();
    }

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 50) / 4f));
	}

    public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfMagicMapping.class, PotionOfPlants.class};
			inQuantity = new int[]{1, 1};
			
			cost = 2;
			
			output = SeasonChange.class;
			outQuantity = 4;
		}
		
	}
}
