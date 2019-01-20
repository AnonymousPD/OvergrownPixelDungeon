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

package com.lovecraftpixel.lovecraftpixeldungeon.items.bombs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Blob;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Regrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Healing;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Splash;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.PotionOfHealing;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfRegrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class RegrowthBomb extends Bomb {
	
	{
		//TODO visuals
		image = ItemSpriteSheet.REGROWTH_BOMB;
	}
	
	@Override
	public boolean explodesDestructively() {
		return false;
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		if (Dungeon.level.heroFOV[cell]) {
			Splash.at(cell, 0x00FF00, 30);
		}
		
		ArrayList<Integer> plantCandidates = new ArrayList<>();
		
		PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				Char ch = Actor.findChar(i);
				if (ch != null){
					if (ch.alignment == Dungeon.hero.alignment) {
						//same as a healing dart
						Buff.affect(ch, Healing.class).setHeal((int) (0.5f * ch.HT + 30), 0.25f, 0);
						PotionOfHealing.cure(ch);
					}
				} else if ( Dungeon.level.map[i] == Terrain.EMPTY ||
							Dungeon.level.map[i] == Terrain.EMBERS ||
							Dungeon.level.map[i] == Terrain.EMPTY_DECO ||
							Dungeon.level.map[i] == Terrain.GRASS ||
							Dungeon.level.map[i] == Terrain.HIGH_GRASS ||
							Dungeon.level.map[i] == Terrain.FURROWED_GRASS){
					
					plantCandidates.add(i);
				}
				GameScene.add( Blob.seed( i, 10, Regrowth.class ) );
			}
		}
		
		Integer plantPos = Random.element(plantCandidates);
		if (plantPos != null){
			Dungeon.level.plant((Plant.Seed) Generator.random(Generator.Category.SEED), plantPos);
			plantCandidates.remove(plantPos);
		}
		
		plantPos = Random.element(plantCandidates);
		if (plantPos != null){
			if (Random.Int(2) == 0){
				Dungeon.level.plant( new WandOfRegrowth.Dewcatcher.Seed(), plantPos);
			} else {
				Dungeon.level.plant((Plant.Seed) Generator.random(Generator.Category.SEED), plantPos);
			}
			plantCandidates.remove(plantPos);
		}
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (20 + 30);
	}
}
