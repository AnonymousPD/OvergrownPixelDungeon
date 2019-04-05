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

package com.lovecraftpixel.lovecraftpixeldungeon.plants;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.TomatoExplosionParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.poisonparticles.TomatobushPoisonParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.bombs.Bomb;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Tomatobush extends Plant {

	{
		image = 22;
	}

	@Override
	public void activate( Char ch ) {

	    explode(ch.pos, null);
	}

    @Override
    public void activate() {
        explode(pos, null);
    }

    public boolean explodesDestructively(){
        return true;
    }

    public void explode(int cell, Char notEffectedChar){

        if (explodesDestructively()) {
            if (Dungeon.level.heroFOV[cell]) {
                CellEmitter.center(cell).burst(TomatoExplosionParticle.FACTORY, 60);
            }

            boolean terrainAffected = false;
            for (int n : PathFinder.NEIGHBOURS9) {
                int c = cell + n;
                if (c >= 0 && c < Dungeon.level.length()) {

                    if (Dungeon.level.flamable[c]) {
                        Dungeon.level.destroy(c);
                        GameScene.updateMap(c);
                        terrainAffected = true;
                    }

                    Char ch = Actor.findChar(c);
                    if (ch != null && ch != notEffectedChar) {
                        //those not at the center of the blast take damage less consistently.
                        int minDamage = c == cell ? Dungeon.depth + 5 : 1;
                        int maxDamage = 10 + Dungeon.depth * 2;

                        int dmg = Random.NormalIntRange(minDamage, maxDamage) - ch.drRoll();
                        if (dmg > 0) {
                            ch.damage(dmg, this);
                        }

                        if (ch == Dungeon.hero && !ch.isAlive())
                            Dungeon.fail(Bomb.class);
                    }
                }
            }

            if (terrainAffected) {
                Dungeon.observe();
            }
        }
    }

    @Override
    public void activatePosionDangerous(Char attacker, Char defender) {
        explode(defender.pos, attacker);
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_TOMATOBUSH;

			plantClass = Tomatobush.class;
			heroDanger = HeroDanger.DANGEROUS;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return TomatobushPoisonParticle.FACTORY;
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
