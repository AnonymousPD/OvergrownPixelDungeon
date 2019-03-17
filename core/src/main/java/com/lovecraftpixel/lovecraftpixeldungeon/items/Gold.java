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

package com.lovecraftpixel.lovecraftpixeldungeon.items;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.Badges;
import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.Statistics;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.MasterThievesArmband;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.GameScene;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.CharSprite;
import com.lovecraftpixel.lovecraftpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Gold extends Item {

	private static final String TXT_VALUE	= "%+d";
	
	{
		image = ItemSpriteSheet.GOLD;
		stackable = true;
	}
	
	public Gold() {
		this( 1 );
	}
	
	public Gold( int value ) {
		this.quantity = value;
        if(this.quantity >= 100){
            setGoldImage(ItemSpriteSheet.GOLD_MUCH);
        } else if(this.quantity >= 50){
            setGoldImage(ItemSpriteSheet.GOLD_SOME);
        } else if(this.quantity >= 25){
            setGoldImage(ItemSpriteSheet.GOLD_SMALL);
        } else if(this.quantity >= 1){
            setGoldImage(ItemSpriteSheet.GOLD_TINY);
        }
	}

	private void setGoldImage(int image){
	    this.image = image;
    }
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		return new ArrayList<String>();
	}
	
	@Override
	public boolean doPickUp( Hero hero ) {
		
		Dungeon.gold += quantity;
		Statistics.goldCollected += quantity;
		Badges.validateGoldCollected();

		MasterThievesArmband.Thievery thievery = hero.buff(MasterThievesArmband.Thievery.class);
		if (thievery != null)
			thievery.collect(quantity);

		GameScene.pickUp( this, hero.pos );
		hero.sprite.showStatus( CharSprite.NEUTRAL, TXT_VALUE, quantity );
		hero.spendAndNext( TIME_TO_PICK_UP );
		
		Sample.INSTANCE.play( Assets.SND_GOLD, 1, 1, Random.Float( 0.9f, 1.1f ) );
		
		return true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public Item random() {
		quantity = Random.Int( 30 + Dungeon.depth * 10, 60 + Dungeon.depth * 20 );
        if(this.quantity >= 100){
            setGoldImage(ItemSpriteSheet.GOLD_MUCH);
        } else if(this.quantity >= 50){
            setGoldImage(ItemSpriteSheet.GOLD_SOME);
        } else if(this.quantity >= 25){
            setGoldImage(ItemSpriteSheet.GOLD_SMALL);
        } else if(this.quantity >= 1){
            setGoldImage(ItemSpriteSheet.GOLD_TINY);
        }
		return this;
	}
	
	private static final String VALUE	= "value";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( VALUE, quantity );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		quantity = bundle.getInt( VALUE );
        if(this.quantity >= 100){
            setGoldImage(ItemSpriteSheet.GOLD_MUCH);
        } else if(this.quantity >= 50){
            setGoldImage(ItemSpriteSheet.GOLD_SOME);
        } else if(this.quantity >= 25){
            setGoldImage(ItemSpriteSheet.GOLD_SMALL);
        } else if(this.quantity >= 1){
            setGoldImage(ItemSpriteSheet.GOLD_TINY);
        }
	}
}
