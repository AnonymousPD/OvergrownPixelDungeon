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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.livingplants.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.GrasslillyPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;

public class Grasslilly extends Plant {

	{
		image = 33;
	}

	@Override
	public void activate( Char ch ) {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            Plant plant = seed.plantClass.newInstance();
            plant.pos = ch.pos;
            plant.activate(ch);
        } catch (Exception e){
            Plant.spawnLasher(pos);
            OvergrownPixelDungeon.reportException(e);
        }

	}

    @Override
    public void activate() {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            Plant plant = seed.plantClass.newInstance();
            plant.pos = pos;
            plant.activate();
        } catch (Exception e){
            Plant.spawnLasher(pos);
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            Plant plant = seed.plantClass.newInstance();
            LivingPlant livingPlant = new LivingPlant().setPlantClass(plant);
            livingPlant.attackProc(enemy, damage);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_GRASSLILLY;

			plantClass = Grasslilly.class;
			heroDanger = HeroDanger.NEUTRAL;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return GrasslillyPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
