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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon.Enchantment;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Grim;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Precise;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Unstable;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.journal.Notes;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.sprites.StatueSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Statue extends Mob {
	
	{
		spriteClass = StatueSprite.class;

		EXP = 0;
		state = PASSIVE;
		
		properties.add(Property.INORGANIC);
	}
	
	protected Weapon weapon;
	
	public Statue() {
		super();
		
		do {
			weapon = (MeleeWeapon) Generator.random(Generator.Category.WEAPON);
		} while (weapon.cursed);
		
		weapon.enchant( Enchantment.random() );
		if(Random.Boolean() == Random.Boolean()){
		    weapon.seed = (Plant.Seed) Generator.random(Generator.Category.SEED).quantity(1);
            weapon.setPoisonTurns(5, false);
        }
		
		HP = HT = 15 + Dungeon.depth * 5;
		defenseSkill = 4 + Dungeon.depth;
	}
	
	private static final String WEAPON	= "weapon";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( WEAPON, weapon );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		weapon = (Weapon)bundle.get( WEAPON );
	}
	
	@Override
	protected boolean act() {
		if (Dungeon.level.heroFOV[pos]) {
			Notes.add( Notes.Landmark.STATUE );
		}
		return super.act();
	}
	
	@Override
	public int damageRoll() {
		return weapon.damageRoll(this);
	}
	
	@Override
	public int attackSkill( Char target ) {
        if (weapon.hasEnchant(Precise.class, this)
                || (weapon.hasEnchant(Unstable.class, this) && Random.Int(11) == 0)){
            if (Precise.rollToGuaranteeHit(weapon)){
                target.sprite.emitter().start( Speck.factory(Speck.LIGHT), 0.05f, 5 );
                return Integer.MAX_VALUE;
            }
            if (weapon.hasEnchant(Unstable.class, this)){
                Unstable.justRolledPrecise = true;
            }
        }
		return (int)((9 + Dungeon.depth) * weapon.accuracyFactor(this));
	}
	
	@Override
	protected float attackDelay() {
		return super.attackDelay()*weapon.speedFactor( this );
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return super.canAttack(enemy) || weapon.canReach(this, enemy.pos);
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, Dungeon.depth + weapon.defenseFactor(this));
	}
	
	@Override
	public void damage( int dmg, Object src ) {

		if (state == PASSIVE) {
			state = HUNTING;
		}
		
		super.damage( dmg, src );
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		return weapon.proc( this, enemy, damage );
	}
	
	@Override
	public void beckon( int cell ) {
		// Do nothing
	}
	
	@Override
	public void die( Object cause ) {
		weapon.identify();
		Dungeon.level.drop( weapon, pos ).sprite.drop();
		super.die( cause );
	}
	
	@Override
	public void destroy() {
		Notes.remove( Notes.Landmark.STATUE );
		super.destroy();
	}
	
	@Override
	public boolean reset() {
		state = PASSIVE;
		return true;
	}

	@Override
	public String description() {
		return Messages.get(this, "desc", weapon.name());
	}
	
	{
		resistances.add(Grim.class);
	}
	
}
