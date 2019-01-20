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
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.ArtifactRecharge;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Recharging;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Lightning;
import com.lovecraftpixel.lovecraftpixeldungeon.items.quest.MetalShard;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.traps.Trap;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.tiles.DungeonTilemap;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ReclaimTrap extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.RECLAIM_TRAP;
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		Trap t = Dungeon.level.traps.get(bolt.collisionPos);
		if (t != null && t.active){
			if (!t.visible) t.reveal();
			t.disarm();
			
			Sample.INSTANCE.play( Assets.SND_LIGHTNING );
			hero.sprite.parent.addToFront( new Lightning(DungeonTilemap.tileCenterToWorld(t.pos), hero.sprite.center(), null) );
			
			ScrollOfRecharging.charge(hero);
			Buff.prolong(hero, Recharging.class, 15f);
			Buff.affect(hero, ArtifactRecharge.class).set( 15 );
			
		} else {
			GLog.w(Messages.get(this, "no_trap"));
		}
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 100) / 3f));
	}
	
	public static class Recipe extends com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfRecharging.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 8;
			
			output = ReclaimTrap.class;
			outQuantity = 3;
		}
		
	}
	
}
