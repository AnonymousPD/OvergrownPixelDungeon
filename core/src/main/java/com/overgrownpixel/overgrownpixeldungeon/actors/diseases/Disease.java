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

package com.overgrownpixel.overgrownpixeldungeon.actors.diseases;

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.ui.DiseaseIndicator;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class Disease extends Actor {

	public Char target;

	{
		actPriority = DISEASE_PRIO; //low priority, towards the end of a turn
        cure = new ArrayList<Plant.Seed>();
	}

	//whether or not the disease announces its name
	public boolean announced = false;

	protected HashSet<Class> resistances = new HashSet<>();

	public HashSet<Class> resistances() {
		return new HashSet<>(resistances);
	}

	protected HashSet<Class> immunities = new HashSet<>();

	public HashSet<Class> immunities() {
		return new HashSet<>(immunities);
	}

	public ArrayList<Plant.Seed> cure;

	public boolean attachTo( Char target ) {

		if (target.isImmune( getClass() )) {
			return false;
		}

		this.target = target;
		target.add( this );

		if (target.diseases().contains(this)){
			if (target.sprite != null) fx( true );
			return true;
		} else {
			this.target = null;
			return false;
		}
	}

	public void detach() {
		if (target.sprite != null) fx( false );
		target.remove( this );
	}

    public void infect(Char target){
        if(this instanceof FlavourDisease){
            FlavourDisease d = (FlavourDisease) this;
            Disease.prolong(target, d.getClass(), d.DURATION);
        } else {
            Disease.affect(target, this.getClass());
        }
        if(target instanceof Hero & target.diseases().contains(this)){
            GLog.n(Messages.get(this, "infected"));
        }
    }

	@Override
	public boolean act() {
		diactivate();
		return true;
	}

	//TODO:
	public int icon() {
		return DiseaseIndicator.NONE;
	}

	public void tintIcon( Image icon ){
		//do nothing by default
	}

	public void fx(boolean on) {
		//do nothing by default
	};

	public String heroMessage(){
		return null;
	}

	public String desc(){
		return "";
	}

	//to handle the common case of showing how many turns are remaining in a buff description.
	protected String dispTurns(float input){
		return new DecimalFormat("#.##").format(input);
	}

    //creates a fresh instance of the buff and attaches that, this allows duplication.
	public static<T extends Disease> T append(Char target, Class<T> diseaseClass ) {
		try {
			T buff = diseaseClass.newInstance();
			buff.attachTo( target );
			return buff;
		} catch (Exception e) {
			OvergrownPixelDungeon.reportException(e);
			return null;
		}
	}

	public static<T extends FlavourDisease> T append( Char target, Class<T> diseaseClass, float duration ) {
		T disease = append( target, diseaseClass );
        disease.spend( duration * target.resist(diseaseClass) );
		return disease;
	}

	//same as append, but prevents duplication.
	public static<T extends Disease> T affect(Char target, Class<T> diseaseClass ) {
		T disease = target.disease( diseaseClass );
		if (disease != null) {
			return disease;
		} else {
			return append( target, diseaseClass );
		}
	}
	
	public static<T extends FlavourDisease> T affect( Char target, Class<T> diseaseClass, float duration ) {
		T disease = affect( target, diseaseClass );
        disease.spend( duration * target.resist(diseaseClass) );
		return disease;
	}

	//postpones an already active buff, or creates & attaches a new buff and delays that.
	public static<T extends FlavourDisease> T prolong( Char target, Class<T> diseaseClass, float duration ) {
		T disease = affect( target, diseaseClass );
        disease.postpone( duration * target.resist(diseaseClass) );
		return disease;
	}
	
	public static void detach( Disease disease ) {
		if (disease != null) {
            disease.detach();
		}
	}
	
	public static void detach( Char target, Class<? extends Disease> cl ) {
		detach( target.disease( cl ) );
	}
}
