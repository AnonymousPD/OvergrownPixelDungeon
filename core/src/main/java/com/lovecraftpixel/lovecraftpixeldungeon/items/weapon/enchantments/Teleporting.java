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

package com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.enchantments;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Actor;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.Char;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.buffs.Invisibility;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.effects.MagicMissile;
import com.lovecraftpixel.lovecraftpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.lovecraftpixel.lovecraftpixeldungeon.items.spells.CrimsonEpithet;
import com.lovecraftpixel.lovecraftpixeldungeon.items.spells.TargetedSpell;
import com.lovecraftpixel.lovecraftpixeldungeon.items.weapon.Weapon;
import com.lovecraftpixel.lovecraftpixeldungeon.mechanics.Ballistica;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.CellSelector;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSprite.Glowing;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.QuickSlotButton;
import com.lovecraftpixel.lovecraftpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class Teleporting extends Weapon.Enchantment {

    protected static int collisionProperties = Ballistica.PROJECTILE;
    protected static Hero curUser = null;
    protected static Weapon curWeapon = null;
    protected static Weapon.Enchantment curEnchantment = null;

	private static Glowing GREENBLUEISH = new Glowing( 0x159F56 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {

	    weapon.hasTeleport = true;
        GLog.p(Messages.get(this, "has_teleport"), Messages.capitalize(weapon.trueName()));

        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.armor.glyph != null){
                comboProc(this, ((Hero) attacker).belongings.armor.glyph, attacker, defender, damage);
            }
        }

		return damage/2;

	}

    public void teleport(Char attacker, Weapon weapon){
        curUser = (Hero) attacker;
        curWeapon = weapon;
        curEnchantment = this;
        weapon.hasTeleport = false;
        GameScene.selectCell(targeter);
    }

    @Override
    public void affectTarget(Ballistica bolt, Hero hero) {
	    if(Dungeon.level.distance(hero.pos, bolt.collisionPos) > curWeapon.level()){
            GLog.w( Messages.get(this, "too_far") );
            return;
        }
        final Char ch = Actor.findChar(bolt.collisionPos);

        if(ch != null){
            if(!ch.properties().contains(Char.Property.IMMOVABLE)){
                int chPos = ch.pos;
                int heroPos = hero.pos;
                ScrollOfTeleportation.appear(ch, heroPos);
                ScrollOfTeleportation.appear(hero, chPos);
            } else {
                GLog.w( Messages.get(CrimsonEpithet.class, "tele_fail") );
            }
        } else {
            ScrollOfTeleportation.appear(hero, bolt.collisionPos);
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
            return Messages.get(TargetedSpell.class, "prompt");
        }
    };
	
	@Override
	public Glowing glowing() {
		return GREENBLUEISH;
	}
}
