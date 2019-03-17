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

package com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Blindness;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Flashing extends Weapon.Enchantment {

	private static ItemSprite.Glowing BRIGHT_YELLOW = new ItemSprite.Glowing( 0xe3d57e );

	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
		// lvl 0 - 20%
		// lvl 1 - 33%
		// lvl 2 - 43%
		int level = Math.max( 0, weapon.level() );

		if (Random.Int( level + 5 ) >= 2) {
            if (Dungeon.level != null) {
                if(weapon.torch_level == 0){
                    weapon.torch_level = Dungeon.level.viewDistance;
                    attacker.sprite.add(CharSprite.State.ILLUMINATED);
                }
                if(weapon.torch_level == Dungeon.level.viewDistance*2){
                    reset_torchlevel(attacker, weapon);
                    Dungeon.observe();
                } else if(weapon.torch_level <= Dungeon.level.viewDistance*2){
                    add_torchlevel(weapon);
                }
            }
		}
		return damage;
	}


    public void add_torchlevel(Weapon weapon){
	    weapon.torch_level++;
    }

    public void reset_torchlevel(Char attacker, Weapon weapon){
        attacker.sprite.remove(CharSprite.State.ILLUMINATED);
        GameScene.flash(0xFFFFFF);
        for (Mob mob : Dungeon.level.mobs){
            if (mob.fieldOfView != null && mob.fieldOfView[attacker.pos]){
                Buff.prolong(mob, Blindness.class, (weapon.level()+5)*2);
                mob.damage(weapon.torch_level, attacker);
            }
        }
        weapon.torch_level = 0;
    }


	@Override
	public ItemSprite.Glowing glowing() {
		return BRIGHT_YELLOW;
	}
}