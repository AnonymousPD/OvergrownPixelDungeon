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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs.livingplants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Roots;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.livingplants.LivingPlantSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.livingplants.LivingPlantSpriteDefault;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.HashSet;

public class LivingPlant extends Mob {

	{

		spriteClass = LivingPlantSpriteDefault.class;
		
		HP = HT = 8;
		defenseSkill = 2;

		EXP = 0;

        properties.add(Property.PLANT);

        becomePlant = false;
	}

	public LivingPlant(){
	    super();
        HP = HT = 8 + Dungeon.depth * 2;
        defenseSkill = 2 + Dungeon.depth * 2;
    }

    public Plant plantClass;

    //mainly used in conjunction with for-loops changing this value directly will ensure results the next time the plant act()'s
    public boolean becomePlant;

    private final String PLANTCLASS = "plantclass";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(PLANTCLASS, plantClass);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        plantClass = (Plant) bundle.get(PLANTCLASS);
    }

    @Override
    public CharSprite sprite() {
        CharSprite sprite = null;
        try {
            sprite = new LivingPlantSprite(plantClass.image);
        } catch (Exception e) {
            OvergrownPixelDungeon.reportException(e);
        }
        return sprite;
    }

    //used to set which effects depending on its source plant the living plant should have
    public LivingPlant setPlantClass(Plant plantClass){
        this.plantClass = plantClass;
        name += (" "+plantClass.plantName);
        return this;
    }

    //sends livingplants sleeping
    public void goToSleep(LivingPlant livingPlant){
        livingPlant.destroy();
        livingPlant.sprite.remove();
        Dungeon.level.plant(livingPlant.plantClass.getPlant(livingPlant.plantClass), pos, false);
    }

    @Override
    public void die(Object cause) {
        if(buff(Roots.class) == null){
            Plant plant = plantClass;
            if(cause instanceof Mob){
                plant.pos = ((Mob) cause).pos;
                plant.activate((Mob) cause);
            } else if(cause instanceof Hero){
                plant.pos = ((Hero) cause).pos;
                plant.activate((Hero) cause);
            } else {
                plant.pos = pos;
                plant.activate();
            }
            if(Random.Float() <= 0.75f){
                Dungeon.level.drop(plantClass.getPlant(plantClass), pos).sprite.drop();
            }
        } else {
            Dungeon.level.drop(plantClass.getPlant(plantClass), pos).sprite.drop();
        }
        super.die(cause);
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        if(!enemy.flying){
            if(Random.Boolean()){
                Plant plant = plantClass;
                plant.pos = enemy.pos;
                if(enemy.HP - damage > 0){
                    plant.activate(enemy);
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
        return super.description() +"\n"+plantClass.desc();
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

            if(Dungeon.level.distance(Dungeon.hero.pos, pos) <= 5){
                enemies.add(Dungeon.hero);
            }

            //if the hero cannot be found and their are no mobs to have as enemies the plant will go back to sleep
            if(enemies.isEmpty()){
                goToSleep(this);
            }

            Char mob = Random.element(enemies);
            if(mob != null){
                if(Dungeon.level.distance(mob.pos, pos) >= Dungeon.level.distance(Dungeon.hero.pos, pos)){
                    if(alignment != Alignment.ALLY){
                        return Dungeon.hero;
                    } else {
                        return mob;
                    }
                } else {
                    return mob;
                }
            }
            return Dungeon.hero;

        } else {
            return enemy;
        }
    }

    @Override
    protected boolean act() {
        //if plants grow roots they become plants again
        if(buff(Roots.class) != null){
            goToSleep(this);
            return true;
        }
        if(becomePlant){
            goToSleep(this);
            return true;
        }
        return super.act();
    }
}
