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

package com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.exotic;

import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Recipe;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.Scroll;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRage;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTerror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.Runestone;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ExoticScroll extends Scroll {
	
	
	public static final HashMap<Class<?extends Scroll>, Class<?extends ExoticScroll>> regToExo = new HashMap<>();
	public static final HashMap<Class<?extends ExoticScroll>, Class<?extends Scroll>> exoToReg = new HashMap<>();
	static{
		regToExo.put(ScrollOfIdentify.class, ScrollOfDivination.class);
		exoToReg.put(ScrollOfDivination.class, ScrollOfIdentify.class);
		
		regToExo.put(ScrollOfUpgrade.class, ScrollOfEnchantment.class);
		exoToReg.put(ScrollOfEnchantment.class, ScrollOfUpgrade.class);
		
		regToExo.put(ScrollOfTerror.class, ScrollOfPetrification.class);
		exoToReg.put(ScrollOfPetrification.class, ScrollOfTerror.class);
		
		regToExo.put(ScrollOfRemoveCurse.class, ScrollOfAntiMagic.class);
		exoToReg.put(ScrollOfAntiMagic.class, ScrollOfRemoveCurse.class);
		
		regToExo.put(ScrollOfLullaby.class, ScrollOfAffection.class);
		exoToReg.put(ScrollOfAffection.class, ScrollOfLullaby.class);
		
		regToExo.put(ScrollOfRage.class, ScrollOfConfusion.class);
		exoToReg.put(ScrollOfConfusion.class, ScrollOfRage.class);
		
		regToExo.put(ScrollOfTerror.class, ScrollOfPetrification.class);
		exoToReg.put(ScrollOfPetrification.class, ScrollOfTerror.class);
		
		regToExo.put(ScrollOfRecharging.class, ScrollOfMysticalEnergy.class);
		exoToReg.put(ScrollOfMysticalEnergy.class, ScrollOfRecharging.class);
		
		regToExo.put(ScrollOfMagicMapping.class, ScrollOfForesight.class);
		exoToReg.put(ScrollOfForesight.class, ScrollOfMagicMapping.class);
		
		regToExo.put(ScrollOfTeleportation.class, ScrollOfPassage.class);
		exoToReg.put(ScrollOfPassage.class, ScrollOfTeleportation.class);
		
		regToExo.put(ScrollOfRetribution.class, ScrollOfPsionicBlast.class);
		exoToReg.put(ScrollOfPsionicBlast.class, ScrollOfRetribution.class);
		
		regToExo.put(ScrollOfMirrorImage.class, ScrollOfPrismaticImage.class);
		exoToReg.put(ScrollOfPrismaticImage.class, ScrollOfMirrorImage.class);
		
		regToExo.put(ScrollOfTransmutation.class, ScrollOfPolymorph.class);
		exoToReg.put(ScrollOfPolymorph.class, ScrollOfTransmutation.class);
	}
	
	@Override
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( exoToReg.get(this.getClass()) ));
	}
	
	@Override
	public void setKnown() {
		if (!isKnown()) {
			handler.know(exoToReg.get(this.getClass()));
			updateQuickslot();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(exoToReg.get(this.getClass()))) {
			image = handler.image(exoToReg.get(this.getClass())) + 16;
			rune = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	public void empoweredRead() {
	
	}
	
	@Override
	//20 gold more than its none-exotic equivalent
	public int price() {
		try {
			return (exoToReg.get(getClass()).newInstance().price() + 20) * quantity;
		} catch (Exception e){
			LovecraftPixelDungeon.reportException(e);
			return 0;
		}
	}
	
	public static class ScrollToExotic extends Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			int r = 0;
			Scroll s = null;
			
			for (Item i : ingredients){
				if (i instanceof Runestone){
					r++;
				} else if (regToExo.containsKey(i.getClass())) {
					s = (Scroll)i;
				}
			}
			
			return s != null && r == 2;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = null;
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
				if (regToExo.containsKey(i.getClass())) {
					try {
						result = regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						LovecraftPixelDungeon.reportException(e);
					}
				}
			}
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (regToExo.containsKey(i.getClass())) {
					try {
						return regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						LovecraftPixelDungeon.reportException(e);
					}
				}
			}
			return null;
			
		}
	}
}
