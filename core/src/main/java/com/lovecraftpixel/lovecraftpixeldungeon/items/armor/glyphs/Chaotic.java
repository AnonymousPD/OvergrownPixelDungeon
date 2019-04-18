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

package com.lovecraftpixel.lovecraftpixeldungeon.items.armor.glyphs;

import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor.Glyph;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Unstable;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;

public class Chaotic extends Glyph {

    public int proc(Armor armor, Char attacker, Char defender, int damage) {
        try {
            if(attacker instanceof Hero){
                if(defender instanceof Hero){
                    if(((Hero) defender).belongings.weapon instanceof Weapon){
                        if(((Weapon) ((Hero) defender).belongings.weapon).enchantment != null){
                            Weapon.Enchantment.comboProc(((Weapon) ((Hero) defender).belongings.weapon).enchantment, this, defender, attacker, damage);
                        }
                    }
                }
            }
            return Glyph.random().proc(armor, attacker, defender, damage);
        } catch (Exception e) {
            LovecraftPixelDungeon.reportException(e);
            if(attacker instanceof Hero){
                if(defender instanceof Hero){
                    if(((Hero) defender).belongings.weapon instanceof Weapon){
                        if(((Weapon) ((Hero) defender).belongings.weapon).enchantment != null){
                            Weapon.Enchantment.comboProc(((Weapon) ((Hero) defender).belongings.weapon).enchantment, this, defender, attacker, damage);
                        }
                    }
                }
            }
            return damage;
        }
    }

    public ItemSprite.Glowing glowing() {
        try {
            return Glyph.random().glowing();
        } catch (Throwable e) {
            LovecraftPixelDungeon.reportException(e);
            return new Unstable().glowing();
        }
    }
}
