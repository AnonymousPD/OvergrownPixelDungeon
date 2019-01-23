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
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor;
import com.lovecraftpixel.lovecraftpixeldungeon.items.armor.Armor.Glyph;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments.Unstable;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Chaotic extends Glyph {
    private static Class<? extends Glyph>[] randomGlyphs = new Class[]{Affection.class, AntiMagic.class, Brimstone.class, Camouflage.class, Entanglement.class, Flow.class, Obfuscation.class, Potential.class, Repulsion.class, Stone.class, Swiftness.class, Thorns.class, Viscosity.class, Cloning.class, Deflection.class, Explosion.class};

    public int proc(Armor armor, Char attacker, Char defender, int damage) {
        try {
            return ((Glyph) ((Class) Random.oneOf(randomGlyphs)).newInstance()).proc(armor, attacker, defender, damage);
        } catch (Exception e) {
            LovecraftPixelDungeon.reportException(e);
        }
        return damage;
    }

    public ItemSprite.Glowing glowing() {
        try {
            return ((Glyph) ((Class) Random.oneOf(randomGlyphs)).newInstance()).glowing();
        } catch (Throwable e) {
            LovecraftPixelDungeon.reportException(e);
            return new Unstable().glowing();
        }
    }
}
