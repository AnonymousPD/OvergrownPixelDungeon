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

package com.lovecraftpixel.lovecraftpixeldungeon.items.potions.alchemy;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Blob;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Regrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.items.potions.Potion;
import com.lovecraftpixel.lovecraftpixeldungeon.items.wands.WandOfRegrowth;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Level;
import com.lovecraftpixel.lovecraftpixeldungeon.levels.Terrain;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Starflower;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.HashSet;
import java.util.Iterator;

public class PotionOfPlants extends Potion {

	{
		initials = 15;
	}

    //the actual affected cells
    private HashSet<Integer> affectedCells;
    //the cells to trace growth particles to, for visual effects.
    private HashSet<Integer> visualCells;

    @Override
    public void shatter(int cell) {

        affectedCells = new HashSet<>();
        visualCells = new HashSet<>();
        if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        if (Dungeon.level.passable[cell]) {
            affectedCells.add(cell);
            spreadRegrowth(cell);
        } else {
            visualCells.add(cell);
        }

        grow();

    }

    @Override
    public void apply(Hero hero) {
        affectedCells = new HashSet<>();
        visualCells = new HashSet<>();

        if (Dungeon.level.passable[hero.pos]) {
            affectedCells.add(hero.pos);
            spreadRegrowth(hero.pos);
        } else {
            visualCells.add(hero.pos);
        }

        grow();
    }

    private void grow() {

        //ignore tiles which can't have anything grow in them.
        for (Iterator<Integer> i = affectedCells.iterator(); i.hasNext();) {
            int c = Dungeon.level.map[i.next()];
            if (!(c == Terrain.EMPTY ||
                    c == Terrain.EMBERS ||
                    c == Terrain.EMPTY_DECO ||
                    c == Terrain.GRASS ||
                    c == Terrain.HIGH_GRASS ||
                    c == Terrain.FURROWED_GRASS)) {
                i.remove();
            }
        }

        float numPlants, numDews, numPods, numStars;

        numPlants = 3;
        numDews = 1;
        numPods = 1;
        numStars = 1;

        placePlants(numPlants, numDews, numPods, numStars);

        for (int i : affectedCells){
            int c = Dungeon.level.map[i];
            if (c == Terrain.EMPTY ||
                    c == Terrain.EMBERS ||
                    c == Terrain.EMPTY_DECO) {
                Level.set( i, Terrain.GRASS );
            }

            GameScene.add(Blob.seed(i, 10, Regrowth.class));

        }
    }

    private void placePlants(float numPlants, float numDews, float numPods, float numStars){
        Iterator<Integer> cells = affectedCells.iterator();
        Level floor = Dungeon.level;

        while(cells.hasNext() && Random.Float() <= numPlants){
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            floor.plant(seed, cells.next(), false);

            numPlants --;
        }

        while (cells.hasNext() && Random.Float() <= numDews){
            floor.plant(new WandOfRegrowth.Dewcatcher.Seed(), cells.next(), false);
            numDews --;
        }

        while (cells.hasNext() && Random.Float() <= numPods){
            floor.plant(new WandOfRegrowth.Seedpod.Seed(), cells.next(), false);
            numPods --;
        }

        while (cells.hasNext() && Random.Float() <= numStars){
            floor.plant(new Starflower.Seed(), cells.next(), false);
            numStars --;
        }

    }

    private void spreadRegrowth(int cell){
        for(int i : PathFinder.NEIGHBOURS8){
            if (Dungeon.level.passable[cell+i]){
                affectedCells.add(cell+i);
            } else if (!Dungeon.level.passable[cell+i]){
                visualCells.add(cell+i);
            }
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
