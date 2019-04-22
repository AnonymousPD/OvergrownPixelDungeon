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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SoulMark;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.PurpleParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.Wand;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.CellSelector;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.ItemSprite.Glowing;
import com.overgrownpixel.overgrownpixeldungeon.ui.QuickSlotButton;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Disintegrating extends Weapon.Enchantment {

    protected static int collisionProperties = Ballistica.PROJECTILE;
    protected static Hero curUser = null;
    protected static Weapon.Enchantment curEnchantment = null;
    protected static Weapon curWeapon = null;

	private static Glowing PALEPURPLEISH = new Glowing( 0x9291B2 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {

	    weapon.hasDisintegrate = true;
        GLog.p(Messages.get(this, "has_disintegrate", Messages.capitalize(weapon.trueName())));

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

        return damage/2;
	}

	public void disintegrate(Char attacker, Weapon weapon){
        curUser = (Hero) attacker;
        curEnchantment = this;
        curWeapon = weapon;
        weapon.hasDisintegrate = false;
        GameScene.selectCell(targeter);
    }

    @Override
    public void affectTarget(Ballistica bolt, Hero hero) {
        boolean terrainAffected = false;

        int level = curWeapon.level();

        int maxDistance = Math.min(curWeapon.level()*2+4, bolt.dist);

        ArrayList<Char> chars = new ArrayList<>();

        int terrainPassed = 2, terrainBonus = 0;
        for (int c : bolt.subPath(1, maxDistance)) {

            Char ch;
            if ((ch = Actor.findChar( c )) != null) {

                //we don't want to count passed terrain after the last enemy hit. That would be a lot of bonus levels.
                //terrainPassed starts at 2, equivalent of rounding up when /3 for integer arithmetic.
                terrainBonus += terrainPassed/3;
                terrainPassed = terrainPassed%3;

                chars.add( ch );
            }

            if (Dungeon.level.flamable[c]) {

                Dungeon.level.destroy( c );
                GameScene.updateMap( c );
                terrainAffected = true;

            }

            if (Dungeon.level.solid[c])
                terrainPassed++;

            CellEmitter.center( c ).burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
        }

        if (terrainAffected) {
            Dungeon.observe();
        }

        int lvl = level + (chars.size()-1) + terrainBonus;
        for (Char ch : chars) {
            processSoulMark(ch, curWeapon.level());
            ch.damage( damageRoll(lvl), this );
            ch.sprite.centerEmitter().burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
            ch.sprite.flash();
        }
    }

    public int damageRoll(int lvl){
        return Random.NormalIntRange(lvl-1, lvl+1);
    }

    protected void processSoulMark(Char target, int chargesUsed){
        if (target != Dungeon.hero &&
                Dungeon.hero.subClass == HeroSubClass.WARLOCK &&
                //standard 1 - 0.92^x chance, plus 7%. Starts at 15%
                Random.Float() > (Math.pow(0.92f, (curWeapon.level()*chargesUsed)+1) - 0.07f)){
            SoulMark.prolong(target, SoulMark.class, SoulMark.DURATION + curWeapon.level());
        }
    }

    protected static void fx(Ballistica bolt, Callback callback ) {
        MagicMissile.boltFromChar( curUser.sprite.parent,
                MagicMissile.MAGIC_MISSILE,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play( Assets.SND_ZAP );
    }

    private static CellSelector.Listener targeter = new  CellSelector.Listener(){

        @Override
        public void onSelect( Integer target ) {

            if (target != null) {

                final Ballistica shot = new Ballistica( curUser.pos, target, collisionProperties);
                int cell = shot.collisionPos;

                curUser.sprite.zap(cell);

                //attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
                if (Actor.findChar(target) != null)
                    QuickSlotButton.target(Actor.findChar(target));
                else
                    QuickSlotButton.target(Actor.findChar(cell));

                curUser.busy();
                Invisibility.dispel();

                fx(shot, new Callback() {
                    public void call() {
                        curEnchantment.affectTarget(shot, curUser);
                        curUser.spendAndNext( 1f );
                    }
                });

            }

        }

        @Override
        public String prompt() {
            return Messages.get(Wand.class, "prompt");
        }
    };
	
	@Override
	public Glowing glowing() {
		return PALEPURPLEISH;
	}
}
