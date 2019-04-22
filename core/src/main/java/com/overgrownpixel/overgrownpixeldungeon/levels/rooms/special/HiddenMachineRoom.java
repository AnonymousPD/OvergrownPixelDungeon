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

package com.overgrownpixel.overgrownpixeldungeon.levels.rooms.special;

import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.M8MirrorMachine;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;

public class HiddenMachineRoom extends SpecialRoom {

    @Override
    public int minWidth() {
        return 9;
    }

    @Override
    public int maxWidth() {
        return 9;
    }

    @Override
    public int minHeight() {
        return 9;
    }

    @Override
    public int maxHeight() {
        return 9;
    }

    public void paint(Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );
        Painter.fill( level, this, 2, Terrain.BOOKSHELF );
        Painter.fill( level, this, 3, Terrain.EMPTY_DECO );
		
		Door entrance = entrance();

		Painter.drawInside(level, this, entrance, 1, Terrain.EMPTY_SP );
		
		entrance.set( Door.Type.HIDDEN );

        M8MirrorMachine mirrorMachine = new M8MirrorMachine();
        mirrorMachine.pos = level.pointToCell(center());
        level.mobs.add( mirrorMachine );
	}
}
