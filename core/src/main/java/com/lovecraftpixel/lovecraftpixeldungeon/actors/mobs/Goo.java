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

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.Blob;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.blobs.GooWarn;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Buff;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.LockedFloor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Ooze;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.CellEmitter;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.Speck;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.particles.ElmoParticle;
import com.lovecraftpixel.lovecraftpixeldungeon.items.keys.SkeletonKey;
import com.lovecraftpixel.lovecraftpixeldungeon.items.quest.GooBlob;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Apricobush;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Fadeleaf;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Musclemoss;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Nightshadeonion;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Starflower;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Sungrass;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Swiftthistle;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.GooSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.BossHealthBar;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.BArray;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.Arrays;
import java.util.HashSet;

public class Goo extends Mob {

	{
		HP = HT = 100;
		EXP = 10;
		defenseSkill = 8;
		spriteClass = GooSprite.class;

		properties.add(Property.BOSS);
		properties.add(Property.DEMONIC);
		properties.add(Property.ACIDIC);

        beneficialPlants = new HashSet<Class>(Arrays.asList(
                Sungrass.class, Starflower.class, Swiftthistle.class,
                Musclemoss.class, Nightshadeonion.class, Fadeleaf.class,
                Apricobush.class
        ));
	}

	private int pumpedUp = 0;

	@Override
	public int damageRoll() {
		int min = 1;
		int max = (HP*2 <= HT) ? 15 : 10;
		if (pumpedUp > 0) {
			pumpedUp = 0;
			PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.solid, null ), 2 );
			for (int i = 0; i < PathFinder.distance.length; i++) {
				if (PathFinder.distance[i] < Integer.MAX_VALUE)
					CellEmitter.get(i).burst(ElmoParticle.FACTORY, 10);
			}
			Sample.INSTANCE.play( Assets.SND_BURNING );
			return Random.NormalIntRange( min*3, max*3 );
		} else {
			return Random.NormalIntRange( min, max );
		}
	}

	@Override
	public int attackSkill( Char target ) {
		int attack = 10;
		if (HP*2 <= HT) attack = 15;
		if (pumpedUp > 0) attack *= 2;
		return attack;
	}

	@Override
	public int defenseSkill(Char enemy) {
		return (int)(super.defenseSkill(enemy) * ((HP*2 <= HT)? 1.5 : 1));
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	public boolean act() {

		if (Dungeon.level.water[pos] && HP < HT) {
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			if (HP*2 == HT) {
				BossHealthBar.bleed(false);
				((GooSprite)sprite).spray(false);
			}
			HP++;
		}

		return super.act();
	}

	@Override
	protected boolean canAttack( Char enemy ) {
		return (pumpedUp > 0) ? distance( enemy ) <= 2 : super.canAttack(enemy);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Ooze.class ).set( 20f );
			enemy.sprite.burst( 0x000000, 5 );
		}

		if (pumpedUp > 0) {
			Camera.main.shake( 3, 0.2f );
		}

		return damage;
	}

	@Override
	protected boolean doAttack( Char enemy ) {
		if (pumpedUp == 1) {
			((GooSprite)sprite).pumpUp();
			PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.solid, null ), 2 );
			for (int i = 0; i < PathFinder.distance.length; i++) {
				if (PathFinder.distance[i] < Integer.MAX_VALUE)
					GameScene.add(Blob.seed(i, 2, GooWarn.class));
			}
			pumpedUp++;

			spend( attackDelay() );

			return true;
		} else if (pumpedUp >= 2 || Random.Int( (HP*2 <= HT) ? 2 : 5 ) > 0) {

			boolean visible = Dungeon.level.heroFOV[pos];

			if (visible) {
				if (pumpedUp >= 2) {
					((GooSprite) sprite).pumpAttack();
				}
				else
					sprite.attack( enemy.pos );
			} else {
				attack( enemy );
			}

			spend( attackDelay() );

			return !visible;

		} else {

			pumpedUp++;

			((GooSprite)sprite).pumpUp();

			for (int i=0; i < PathFinder.NEIGHBOURS9.length; i++) {
				int j = pos + PathFinder.NEIGHBOURS9[i];
				if (!Dungeon.level.solid[j]) {
					GameScene.add(Blob.seed(j, 2, GooWarn.class));
				}
			}

			if (Dungeon.level.heroFOV[pos]) {
				sprite.showStatus( CharSprite.NEGATIVE, Messages.get(this, "!!!") );
				GLog.n( Messages.get(this, "pumpup") );
			}

			spend( attackDelay() );

			return true;
		}
	}

	@Override
	public boolean attack( Char enemy ) {
		boolean result = super.attack( enemy );
		pumpedUp = 0;
		return result;
	}

	@Override
	protected boolean getCloser( int target ) {
		pumpedUp = 0;
		return super.getCloser( target );
	}
	
	@Override
	public void move( int step ) {
		Dungeon.level.seal();
		super.move( step );
	}

	@Override
	public void damage(int dmg, Object src) {
		boolean bleeding = (HP*2 <= HT);
		super.damage(dmg, src);
		if ((HP*2 <= HT) && !bleeding){
			BossHealthBar.bleed(true);
			sprite.showStatus(CharSprite.NEGATIVE, Messages.get(this, "enraged"));
			((GooSprite)sprite).spray(true);
			yell(Messages.get(this, "gluuurp"));
		}
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null) lock.addTime(dmg*2);
	}

	@Override
	public void die( Object cause ) {
		
		super.die( cause );
		
		Dungeon.level.unseal();
		
		GameScene.bossSlain();
		Dungeon.level.drop( new SkeletonKey( Dungeon.depth ), pos ).sprite.drop();
		
		//60% chance of 2 blobs, 30% chance of 3, 10% chance for 4. Average of 2.5
		int blobs = Random.chances(new float[]{0, 0, 6, 3, 1});
		for (int i = 0; i < blobs; i++){
			int ofs;
			do {
				ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
			} while (!Dungeon.level.passable[pos + ofs]);
			Dungeon.level.drop( new GooBlob(), pos + ofs ).sprite.drop( pos );
		}
		
		Badges.validateBossSlain();
		
		yell( Messages.get(this, "defeated") );
	}
	
	@Override
	public void notice() {
		super.notice();
		BossHealthBar.assignBoss(this);
		yell( Messages.get(this, "notice") );
	}

	private final String PUMPEDUP = "pumpedup";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( PUMPEDUP , pumpedUp );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		super.restoreFromBundle( bundle );

		pumpedUp = bundle.getInt( PUMPEDUP );
		if (state != SLEEPING) BossHealthBar.assignBoss(this);
		if ((HP*2 <= HT)) BossHealthBar.bleed(true);

	}
	
}
