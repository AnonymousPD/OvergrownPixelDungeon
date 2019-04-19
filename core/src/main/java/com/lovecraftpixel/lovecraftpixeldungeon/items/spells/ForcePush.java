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

package com.lovecraftpixel.lovecraftpixeldungeon.items.spells;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.alchemy.PotionOfExplosion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfBlastWave;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class ForcePush extends Spell {
	
	{
		image = ItemSpriteSheet.SPONTANEOUS_COMBUSTION;
	}

    @Override
    protected void onCast(Hero hero) {
	    for(Mob mob : Dungeon.level.mobs){
	        if(Dungeon.level.heroFOV[mob.pos]){
                int opposite;
                do{
                    opposite = mob.pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                } while (Dungeon.level.map[opposite] == Terrain.WALL || Dungeon.level.map[opposite] == Terrain.WALL_DECO);
                Ballistica trajectory = new Ballistica(hero.pos, opposite, Ballistica.MAGIC_BOLT);
                WandOfBlastWave.throwChar(mob, trajectory, 100);
            }
        }
        detach( curUser.belongings.backpack );
        updateQuickslot();
    }

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 30) / 16f));
	}

    public static class Recipe extends com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfPsionicBlast.class, PotionOfExplosion.class};
			inQuantity = new int[]{1, 1};
			
			cost = 8;
			
			output = ForcePush.class;
			outQuantity = 16;
		}
		
	}
}
