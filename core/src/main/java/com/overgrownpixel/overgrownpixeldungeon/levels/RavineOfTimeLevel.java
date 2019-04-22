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

package com.overgrownpixel.overgrownpixeldungeon.levels;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.levels.rooms.standard.EmptyRoom;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.tiles.CustomTiledVisual;
import com.watabou.utils.Bundle;

import java.util.Calendar;

public class RavineOfTimeLevel extends Level {
	
	{
		color1 = 0x4b6636;
		color2 = 0xf2f2f2;
		viewDistance = 100;
	}

    public boolean day(){
        if(Calendar.HOUR_OF_DAY > 4 && Calendar.HOUR_OF_DAY < 20){
            return true;
        }
        return false;
    }

    @Override
    public void updateFieldOfView(Char c, boolean[] fieldOfView) {
        if(day()){
            for (int i = 0; i < length(); i ++){
                fieldOfView[i] = true;
            }
        } else {
            super.updateFieldOfView(c, fieldOfView);
        }
    }

    @Override
	public String tilesTex() {
		return Assets.TILES_OVERWORLD;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_OVERWORLD;
	}
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
	}

    Village village = new Village();
	
	@Override
	protected boolean build() {
		
		setSize(26, 25);

        map = MAP.clone();

        exit = entrance = 0;
        for (int i = 0; i < length(); i ++)
            if (map[i] == Terrain.ENTRANCE)
                entrance = i;
            else if (map[i] == Terrain.EXIT)
                exit = i;

        buildFlagMaps();
        cleanWalls();
		
		return true;
	}

    @Override
	public Mob createMob() {
		return null;
	}

    @Override
    public void create() {
        super.create();
        village.pos(11, 4);
        this.customTiles.add(village);
    }

    @Override
	protected void createMobs() {
	}
	
	public Actor respawner() {
		return null;
	}
	
	@Override
	protected void createItems() {

	}

    @Override
    public void press(int cell, Char ch) {
        super.press(cell, ch);

        if((new EmptyRoom().set(10, 3, 16, 11).inside(cellToPoint(cell)))){
            village.remove();
            this.customTiles.remove(village);
        } else {
            this.customTiles.add(village);
            ((GameScene) OvergrownPixelDungeon.scene()).addCustomTile(village);
        }
    }

    @Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(RavineOfTimeLevel.class, "water_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(RavineOfTimeLevel.class, "high_grass_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(RavineOfTimeLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(RavineOfTimeLevel.class, "exit_desc");
			case Terrain.WALL_DECO:
			case Terrain.EMPTY_DECO:
				return Messages.get(RavineOfTimeLevel.class, "deco_desc");
			case Terrain.EMPTY_SP:
				return Messages.get(RavineOfTimeLevel.class, "sp_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(RavineOfTimeLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(RavineOfTimeLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}

    private static final int E = Terrain.PIT;
    private static final int W = Terrain.EMPTY;
    private static final int x = Terrain.ENTRANCE;
    private static final int y = Terrain.EXIT;

    private static final int[] MAP =
            {       E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, W, W, W, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, W, W, W, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, W, W, W, W, W, W, W, E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E,
                    W, W, E, E, E, E, W, W, W, W, W, W, W, W, W, W, W, E, E, E, E, E, E, E, E, E,
                    W, W, W, E, E, E, E, E, E, E, E, E, E, E, W, W, W, E, E, E, E, E, E, E, E, E,
                    E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E,
                    E, W, E, E, E, E, E, E, E, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E,
                    E, W, E, E, E, W, W, E, W, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E,
                    E, W, W, W, W, W, W, W, W, E, E, E, E, E, E, W, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, W, W, E, E, E, E, E, E, W, W, W, W, W, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, W, W, W, W, W, W, W, W, W, W, W, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, W, W, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
                    E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
            };

    public static class Village extends CustomTiledVisual {

        public Village(){
            super( Assets.HOUSE_1 );
        }

        @Override
        public CustomTiledVisual create() {
            tileH = 7;
            tileW = 5;
            mapSimpleImage(0, 0);
            return super.create();
        }

        @Override
        public String name(int tileX, int tileY) {
            return Messages.get(RavineOfTimeLevel.class, "village_name");
        }

        @Override
        public String desc(int tileX, int tileY) {
            return Messages.get(RavineOfTimeLevel.class, "village_desc");
        }
    }
}
