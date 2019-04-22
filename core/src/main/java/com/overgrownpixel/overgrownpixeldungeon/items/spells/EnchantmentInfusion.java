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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.effects.Enchanting;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfEnchantment;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

public class EnchantmentInfusion extends InventorySpell {
	
	{
		image = ItemSpriteSheet.ENCHANT_INFUSE;
		mode = WndBag.Mode.ENCHANTABLE;
	}
	
	@Override
	protected void onItemSelected(final Item item) {
        if (item instanceof Weapon){

            final Weapon.Enchantment enchants[] = new Weapon.Enchantment[5];

            Class<? extends Weapon.Enchantment> existing = ((Weapon) item).enchantment != null ? ((Weapon) item).enchantment.getClass() : null;
            enchants[0] = Weapon.Enchantment.randomCommon( existing );
            enchants[1] = Weapon.Enchantment.randomUncommon( existing );
            enchants[2] = Weapon.Enchantment.random( existing, enchants[0].getClass(), enchants[1].getClass());
            enchants[3] = Weapon.Enchantment.random( existing, enchants[0].getClass(), enchants[1].getClass(), enchants[2].getClass());
            enchants[4] = Weapon.Enchantment.random( existing, enchants[0].getClass(), enchants[1].getClass(), enchants[2].getClass(), enchants[3].getClass());

            GameScene.show(new WndOptions(Messages.titleCase(EnchantmentInfusion.this.name()),
                    Messages.get(ScrollOfEnchantment.class, "weapon") +
                            "\n\n" +
                            Messages.get(ScrollOfEnchantment.class, "cancel_warn"),
                    enchants[0].name(),
                    enchants[1].name(),
                    enchants[2].name(),
                    enchants[3].name(),
                    enchants[4].name(),
                    Messages.get(ScrollOfEnchantment.class, "cancel")){

                @Override
                protected void onSelect(int index) {
                    if (index < 3) {
                        ((Weapon) item).enchant(enchants[index]);
                        GLog.p(Messages.get(StoneOfEnchantment.class, "weapon"));

                        Sample.INSTANCE.play( Assets.SND_READ );
                        Invisibility.dispel();
                        Enchanting.show(curUser, item);
                    }
                }

                @Override
                public void onBackPressed() {
                    //do nothing, reader has to cancel
                }
            });

        } else if (item instanceof Armor) {

            final Armor.Glyph glyphs[] = new Armor.Glyph[5];

            Class<? extends Armor.Glyph> existing = ((Armor) item).glyph != null ? ((Armor) item).glyph.getClass() : null;
            glyphs[0] = Armor.Glyph.randomCommon( existing );
            glyphs[1] = Armor.Glyph.randomUncommon( existing );
            glyphs[2] = Armor.Glyph.random( existing, glyphs[0].getClass(), glyphs[1].getClass());
            glyphs[3] = Armor.Glyph.random( existing, glyphs[0].getClass(), glyphs[1].getClass(), glyphs[2].getClass());
            glyphs[4] = Armor.Glyph.random( existing, glyphs[0].getClass(), glyphs[1].getClass(), glyphs[2].getClass(), glyphs[3].getClass());

            GameScene.show(new WndOptions(Messages.titleCase(EnchantmentInfusion.this.name()),
                    Messages.get(ScrollOfEnchantment.class, "armor") +
                            "\n\n" +
                            Messages.get(ScrollOfEnchantment.class, "cancel_warn"),
                    glyphs[0].name(),
                    glyphs[1].name(),
                    glyphs[2].name(),
                    glyphs[3].name(),
                    glyphs[4].name(),
                    Messages.get(ScrollOfEnchantment.class, "cancel")){

                @Override
                protected void onSelect(int index) {
                    if (index < 3) {
                        ((Armor) item).inscribe(glyphs[index]);
                        GLog.p(Messages.get(StoneOfEnchantment.class, "armor"));

                        Sample.INSTANCE.play( Assets.SND_READ );
                        Invisibility.dispel();
                        Enchanting.show(curUser, item);
                    }
                }

                @Override
                public void onBackPressed() {
                    //do nothing, reader has to cancel
                }
            });
        } else {
            //TODO if this can ever be found un-IDed, need logic for that
            curItem.collect();
        }
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 30) / 2f));
	}
	
	public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfEnchantment.class, ScrollOfIdentify.class};
			inQuantity = new int[]{1, 1};
			
			cost = 1;
			
			output = EnchantmentInfusion.class;
			outQuantity = 2;
		}
		
	}
}
