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

import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;

public class CrimsonEpithet extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.CRIMSON_EPITHET;
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		final Char ch = Actor.findChar(bolt.collisionPos);

		if(ch != null){
		    if(!ch.properties().contains(Char.Property.IMMOVABLE)){
		        int chPos = ch.pos;
		        int heroPos = hero.pos;
                ScrollOfTeleportation.appear(ch, heroPos);
                ScrollOfTeleportation.appear(hero, chPos);
            } else {
                GLog.w( Messages.get(this, "tele_fail") );
            }
        } else {
            ScrollOfTeleportation.appear(hero, bolt.collisionPos);
        }
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 30) / 6f));
	}
	
	public static class Recipe extends com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfTeleportation.class, ScrollOfMagicMapping.class};
			inQuantity = new int[]{1, 1};
			
			cost = 3;
			
			output = CrimsonEpithet.class;
			outQuantity = 6;
		}
		
	}
	
}
