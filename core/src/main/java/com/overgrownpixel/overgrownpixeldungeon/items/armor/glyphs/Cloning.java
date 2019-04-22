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

package com.overgrownpixel.overgrownpixeldungeon.items.armor.glyphs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor.Glyph;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;

public class Cloning extends Glyph {

	private static Glowing ORANGE = new Glowing( 0xDF9D00 );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage) {

		int level = Math.max(0, armor.level());
		
		if (Random.Int( level / 2 + 10 ) >= 9) {

            Collection arraylist = new ArrayList();
            for(int p : PathFinder.NEIGHBOURS8){
                int pos = defender.pos + p;
                if(Actor.findChar(pos) == null && (Dungeon.level.passable[pos] || Dungeon.level.avoid[pos])){
                    arraylist.add(Integer.valueOf(pos));
                }
            }
            if(!arraylist.isEmpty()){
                Mob mirrorImage = new MirrorImage();
                ((MirrorImage) mirrorImage).duplicate((Hero) defender);
                if (mirrorImage != null) {
                    GameScene.add(mirrorImage);
                    ScrollOfTeleportation.appear(mirrorImage, ((Integer) Random.element(arraylist)).intValue());
                }
            }

		}

        if(defender instanceof Hero){
            if(((Hero) defender).belongings.weapon instanceof Weapon){
                if(((Weapon) ((Hero) defender).belongings.weapon).enchantment != null){
                    Weapon.Enchantment.comboProc(((Weapon) ((Hero) defender).belongings.weapon).enchantment, this, defender, attacker, damage);
                }
            }
        }
		
		return damage;
	}

	@Override
	public Glowing glowing() {
		return ORANGE;
	}
}
