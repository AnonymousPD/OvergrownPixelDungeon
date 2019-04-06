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

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.ShadowParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTerror;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class DoomCall extends Spell {
	
	{
		image = ItemSpriteSheet.DOOMCALL;
	}

    @Override
    protected void onCast(Hero hero) {
        ArrayList<Mob> mobs = new ArrayList<>();
        for(Mob mob : Dungeon.level.mobs){
            mobs.add(mob);
        }
        Random.shuffle(mobs);
        Mob mob = Random.element(mobs);
        if(!mob.properties().contains(Char.Property.BOSS) && !mob.properties().contains(Char.Property.MINIBOSS)){
            mob.die(hero);
            if (Dungeon.level.heroFOV[mob.pos]) {
                Sample.INSTANCE.play( Assets.SND_HIT );
                mob.sprite.emitter().burst( ShadowParticle.CURSE, 6);
                GLog.p(Messages.get(this, "crushed", mob.name));
            }
        }
        detach( curUser.belongings.backpack );
        updateQuickslot();
    }

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 30) / 6f));
	}

    public static class Recipe extends com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfRage.class, ScrollOfTerror.class};
			inQuantity = new int[]{1, 1};
			
			cost = 3;
			
			output = DoomCall.class;
			outQuantity = 6;
		}
		
	}
}
