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

package com.lovecraftpixel.lovecraftpixeldungeon.windows;

import com.lovecraftpixel.lovecraftpixeldungeon.Dungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.mobs.npcs.Gardner;
import com.lovecraftpixel.lovecraftpixeldungeon.items.Item;
import com.lovecraftpixel.lovecraftpixeldungeon.items.artifacts.SandalsOfNature;
import com.lovecraftpixel.lovecraftpixeldungeon.journal.Notes;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
import com.lovecraftpixel.lovecraftpixeldungeon.plants.Plant;
import com.lovecraftpixel.lovecraftpixeldungeon.scenes.PixelScene;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RedButton;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.RenderedTextMultiline;
import com.lovecraftpixel.lovecraftpixeldungeon.ui.Window;

import java.util.ArrayList;

public class WndGardner extends Window {

	private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 20;
	private static final float GAP		= 2;

	public WndGardner(final Gardner gardner, boolean hasCompletedQuest, int amount ) {
		
		super();
		
		IconTitle titlebar = new IconTitle();
		titlebar.icon(gardner.sprite());
		titlebar.label(gardner.name);
		titlebar.setRect(0, 0, WIDTH, 0);
		add( titlebar );

		String msg;
		if (hasCompletedQuest){
			msg = Messages.get(gardner.getClass(), "hascompleted");
		} else {
			msg = Messages.get(gardner.getClass(), "hasntcompleted", Messages.titleCase(new SandalsOfNature().name()), amount);
		}

		RenderedTextMultiline message = PixelScene.renderMultiline( msg, 6 );
		message.maxWidth(WIDTH);
		message.setPos(0, titlebar.bottom() + GAP);
		add( message );

		if(!hasCompletedQuest && hasDifferentSeeds(amount)){
            RedButton btnSandalsOfNature = new RedButton(Messages.titleCase(new SandalsOfNature().name())) {
                @Override
                protected void onClick() {
                    gardner.hasCompletedQuest = true;
                    Dungeon.level.drop(new SandalsOfNature().identify(), gardner.pos);
                    Notes.remove( Notes.Landmark.GARDNER );
                    removeDifferentSeeds();
                }
            };
            btnSandalsOfNature.setRect(0, message.top() + message.height() + GAP, WIDTH, BTN_HEIGHT);
            add( btnSandalsOfNature );

            resize(WIDTH, (int) btnSandalsOfNature.bottom());
        } else {
            resize(WIDTH, (int) message.bottom());
        }
	}

	private void removeDifferentSeeds(){
        ArrayList<Plant.Seed> seeds = new ArrayList<Plant.Seed>();
        for(Item item : Dungeon.hero.belongings.backpack){
            if(item instanceof Plant.Seed){
                seeds.add((Plant.Seed)item);
            }
        }
        for(Item s : seeds){
            s.detach(Dungeon.hero.belongings.backpack);
        }
        hide();
    }

	private boolean hasDifferentSeeds(int amount){
	    int differentSeeds = 0;
        ArrayList<Class> seedsFound = new ArrayList<Class>();
	    for(Item item : Dungeon.hero.belongings.backpack){
	        if(item instanceof Plant.Seed && !seedsFound.contains(item.getClass())){
	            seedsFound.add(item.getClass());
	            differentSeeds++;
                if(differentSeeds == amount){
                    return true;
                }
            }
        }
	    return false;
    }
}
