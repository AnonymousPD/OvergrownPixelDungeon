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

package com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Amok;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Burning;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Charm;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Terror;
import com.lovecraftpixel.lovecraftpixeldungeon.items.bombs.Bomb;
import com.lovecraftpixel.lovecraftpixeldungeon.items.stones.StoneOfAggression;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Firefoxglove;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.FlameBoiSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.HashSet;

public class FlameB01 extends Mob {

    private static final float TIME_TO_BURN	= 1f;

    public int gasTankPressure;
	
	{
		spriteClass = FlameBoiSprite.class;
        properties.add(Property.INORGANIC);
        properties.add(Property.MACHINE);
		
		HP = HT = 40;
		defenseSkill = 15;
		
		EXP = 10;
		maxLvl = 15;

		gasTankPressure = Random.Int(100, 150);
	}

    @Override
    protected boolean act() {
        if(buff(Burning.class) != null){
            new Bomb().explode(this.pos);
            this.die(Burning.class);
            return true;
        }
        return super.act();
    }

    @Override
    protected boolean canAttack( Char enemy ) {
        return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
    }

    @Override
    protected boolean doAttack( Char enemy ) {

        if (Dungeon.level.distance( pos, enemy.pos ) <= 1 || gasTankPressure <= 0) {

            return super.doAttack( enemy );

        } else {

            boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
            if (visible) {
                sprite.attack( enemy.pos );
                spend( TIME_TO_BURN );
                if(!isStupid){
                    new Firefoxglove().shoot(this, enemy.pos);
                    gasTankPressure -= Random.Int(1, 10);
                } else {
                    new Firefoxglove().shoot(this, enemy.pos);
                    gasTankPressure -= Random.Int(10, 20);
                }
            }

            return !visible;
        }
    }

    @Override
    protected Char chooseEnemy() {

        Terror terror = buff( Terror.class );
        if (terror != null) {
            Char source = (Char)Actor.findById( terror.object );
            if (source != null) {
                return source;
            }
        }

        StoneOfAggression.Aggression aggro = buff( StoneOfAggression.Aggression.class );
        if (aggro != null){
            Char source = (Char)Actor.findById( aggro.object );
            if (source != null){
                return source;
            }
        }

        //find a new enemy if..
        boolean newEnemy = false;
        //we have no enemy, or the current one is dead
        if ( enemy == null || !enemy.isAlive() || state == WANDERING)
            newEnemy = true;
            //We are an ally, and current enemy is another ally.
        else if (alignment == Alignment.ALLY && enemy.alignment == Alignment.ALLY)
            newEnemy = true;
            //We are amoked and current enemy is the hero
        else if (buff( Amok.class ) != null && enemy == Dungeon.hero)
            newEnemy = true;
            //We are charmed and current enemy is what charmed us
        else if (buff(Charm.class) != null && buff(Charm.class).object == enemy.id())
            newEnemy = true;

        if ( newEnemy ) {

            HashSet<Char> enemies = new HashSet<>();

            //if the mob is amoked...
            if ( buff(Amok.class) != null) {
                //try to find an enemy mob to attack first.
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ENEMY && mob != this && fieldOfView[mob.pos])
                        enemies.add(mob);

                if (enemies.isEmpty()) {
                    //try to find ally mobs to attack second.
                    for (Mob mob : Dungeon.level.mobs)
                        if (mob.alignment == Alignment.ALLY && mob != this && fieldOfView[mob.pos])
                            enemies.add(mob);

                    if (enemies.isEmpty()) {
                        //try to find the hero third
                        if (fieldOfView[Dungeon.hero.pos]) {
                            enemies.add(Dungeon.hero);
                        }
                    }
                }

                //if the mob is an ally...
            } else if ( alignment == Alignment.ALLY ) {
                //look for hostile mobs that are not passive to attack
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ENEMY
                            && fieldOfView[mob.pos]
                            && mob.state != mob.PASSIVE)
                        enemies.add(mob);

                //if the mob is an enemy...
            } else if (alignment == Alignment.ENEMY) {
                //look for ally mobs to attack
                for (Mob mob : Dungeon.level.mobs)
                    if (mob.alignment == Alignment.ALLY && fieldOfView[mob.pos])
                        enemies.add(mob);

                    //look for bats to kill
                for (Mob mob : Dungeon.level.mobs)
                    if (mob instanceof Bat)
                        enemies.add(mob);

                //and look for the hero
                if (fieldOfView[Dungeon.hero.pos]) {
                    enemies.add(Dungeon.hero);
                }

            }

            Charm charm = buff( Charm.class );
            if (charm != null){
                Char source = (Char)Actor.findById( charm.object );
                if (source != null && enemies.contains(source) && enemies.size() > 1){
                    enemies.remove(source);
                }
            }

            //neutral characters in particular do not choose enemies.
            if (enemies.isEmpty()){
                return null;
            } else {
                //go after the closest potential enemy, preferring the hero if two are equidistant
                Char closest = null;
                for (Char curr : enemies){
                    if (closest == null
                            || Dungeon.level.distance(pos, curr.pos) < Dungeon.level.distance(pos, closest.pos)
                            || Dungeon.level.distance(pos, curr.pos) == Dungeon.level.distance(pos, closest.pos) && curr == Dungeon.hero){
                        closest = curr;
                    }
                }
                return closest;
            }

        } else
            return enemy;
    }

    @Override
	public int attackSkill( Char target ) {
		return 15;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 8);
	}

    @Override
    public String description() {
        return Messages.get(this, "desc", gasTankPressure);
    }

    private static final String PRESSURE     = "pressure";

    @Override
    public void storeInBundle(Bundle bundle) {
	    bundle.put(PRESSURE, gasTankPressure);
        super.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        gasTankPressure = bundle.getInt(PRESSURE);
        super.restoreFromBundle(bundle);
    }
}
