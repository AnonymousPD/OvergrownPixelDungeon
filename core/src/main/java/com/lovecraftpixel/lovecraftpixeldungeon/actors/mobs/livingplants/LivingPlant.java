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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.livingplants;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Roots;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.Mob;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Generator;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.livingplants.LivingPlantSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.livingplants.LivingPlantSpriteDefault;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.HashSet;

public class LivingPlant extends Mob {

	{
		spriteClass = LivingPlantSpriteDefault.class;
		
		HP = HT = 8;
		defenseSkill = 2;
		
		maxLvl = 5;

		EXP = 0;
	}

	public Plant plantClass;
    public int powerlevel;

    private final String PLANTCLASS = "plantclass";
    private final String POWERLEVEL = "powerlevel";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(PLANTCLASS, plantClass);
        bundle.put(POWERLEVEL, powerlevel);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        plantClass = (Plant) bundle.get(PLANTCLASS);
        powerlevel = bundle.getInt(POWERLEVEL);
    }

    @Override
    public CharSprite sprite() {
        CharSprite sprite = null;
        try {
            sprite = new LivingPlantSprite(plantClass.image);
        } catch (Exception e) {
            LovecraftPixelDungeon.reportException(e);
        }
        return sprite;
    }

    public LivingPlant setPlantClass(Plant plantClass, int powerlevel){
        this.plantClass = plantClass;
        this.powerlevel = powerlevel;
        return this;
    }

    @Override
    public void die(Object cause) {
        if(!this.buffs().contains(Roots.class)){
            Plant plant = plantClass;
            plant.pos = pos;
            plant.activate(this);
        }
        super.die(cause);
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        if(!enemy.flying){
            if(powerlevel == 1){
                if(Random.Boolean()) plantClass.activate(enemy);
            } else if(powerlevel > 1){
                Plant.Seed seed;
                for(int i = powerlevel; i > 0; i--){
                    if(Random.Boolean()){
                        seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
                        seed.getPlant(enemy.pos).activate(enemy);
                    }
                }
            }
        }
        return super.attackProc(enemy, damage);
    }

    @Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 4 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 8;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 1);
	}

    @Override
    public String description() {
        return super.description() + plantClass.desc();
    }

    @Override
    protected Char chooseEnemy() {

        //try to find a new enemy in these circumstances
        if (enemy == null || !enemy.isAlive() || state == WANDERING
                || Dungeon.level.distance(enemy.pos, pos) > 2
                || (alignment == Alignment.ALLY && enemy.alignment == Alignment.ALLY)){

            //find all mobs near the living plant
            HashSet<Char> enemies = new HashSet<>();
            for (Mob mob : Dungeon.level.mobs) {
                if (!(mob == this)
                        && Dungeon.level.distance(mob.pos, pos) <= 5
                        && mob.alignment != Alignment.NEUTRAL
                        && !(alignment == Alignment.ALLY && mob.alignment == Alignment.ALLY)) {
                    enemies.add(mob);
                }
            }

            if(enemies.isEmpty()){
                return Dungeon.hero;
            }

            Char mob = Random.element(enemies);
            if(Dungeon.level.distance(mob.pos, pos) >= Dungeon.level.distance(Dungeon.hero.pos, pos)){
                if(alignment != Alignment.ALLY){
                    return Dungeon.hero;
                } else {
                    return mob;
                }
            } else {
                return mob;
            }

        } else {
            return enemy;
        }
    }

    @Override
    protected boolean act() {
        if(this.buffs().contains(Roots.class)){
            die(this);
            Dungeon.level.plant(plantClass.getPlant(plantClass), pos);
        }
        return super.act();
    }
}
