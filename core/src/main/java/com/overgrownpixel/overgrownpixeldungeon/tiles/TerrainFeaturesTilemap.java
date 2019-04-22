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

package com.overgrownpixel.overgrownpixeldungeon.tiles;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.plates.PressurePlate;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.Trap;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.watabou.noosa.Image;
import com.watabou.noosa.tweeners.ScaleTweener;
import com.watabou.utils.PointF;
import com.watabou.utils.RectF;
import com.watabou.utils.SparseArray;

//TODO add in a proper set of vfx for plants growing/withering, grass burning, discovering traps
public class TerrainFeaturesTilemap extends DungeonTilemap {

	private static TerrainFeaturesTilemap instance;

	private SparseArray<Plant> plants;
	private SparseArray<Trap> traps;
    private SparseArray<PressurePlate> plates;

	public TerrainFeaturesTilemap(SparseArray<Plant> plants, SparseArray<Trap> traps, SparseArray<PressurePlate> plates) {
		super(Assets.TERRAIN_FEATURES);

		this.plants = plants;
		this.traps = traps;
		this.plates = plates;

		map( Dungeon.level.map, Dungeon.level.width() );

		instance = this;
	}

    public TerrainFeaturesTilemap() {
        super(Assets.TERRAIN_FEATURES);

        instance = this;
    }

	protected int getTileVisual(int pos, int tile, boolean flat){
		if (traps.get(pos) != null){
			Trap trap = traps.get(pos);
			if (!trap.visible)
				return -1;
			else
				return (trap.active ? trap.color : Trap.BLACK) + (trap.shape * 16);
		}

        if (plates.get(pos) != null){
            if(!plates.get(pos).active){
                return 10*16;
            } else {
                return 1+10*16;
            }
        }

		if (plants.get(pos) != null){
			return plants.get(pos).image + 7*16;
		}

		int stage = (Dungeon.depth-1)/5;
		if (Dungeon.depth == 21) stage--;
		if (tile == Terrain.HIGH_GRASS){
			return 9 + 16*stage + (DungeonTileSheet.tileVariance[pos] >= 50 ? 1 : 0);
		} else if (tile == Terrain.FURROWED_GRASS){
			//TODO
			return 11 + 16*stage + (DungeonTileSheet.tileVariance[pos] >= 50 ? 1 : 0);
		} else if (tile == Terrain.GRASS) {
			return 13 + 16*stage + (DungeonTileSheet.tileVariance[pos] >= 50 ? 1 : 0);
		} else if (tile == Terrain.EMBERS) {
			return 9 * (16*5) + (DungeonTileSheet.tileVariance[pos] >= 50 ? 1 : 0);
		}

		return -1;
	}

	public static Image tile(int pos, int tile ) {
		RectF uv = instance.tileset.get( instance.getTileVisual( pos, tile, true ) );
		if (uv == null) return null;
		
		Image img = new Image( instance.texture );
		img.frame(uv);
		return img;
	}

	public static Image getPlantSprite(int image){
	    TerrainFeaturesTilemap terrainFeaturesTilemap = new TerrainFeaturesTilemap();
        RectF uv = terrainFeaturesTilemap.instance.tileset.get( image + 7*16 );
        if (uv == null) return null;

        Image img = new Image( terrainFeaturesTilemap.instance.texture );
        img.frame(uv);
	    return img;
    }

    public static Image getPressurePlateSprite(boolean active){
        TerrainFeaturesTilemap terrainFeaturesTilemap = new TerrainFeaturesTilemap();
        RectF uv;
        if(active){
            uv = terrainFeaturesTilemap.instance.tileset.get( 10*16 );
        } else {
           uv = terrainFeaturesTilemap.instance.tileset.get( 1+10*16 );
        }
        if (uv == null) return null;

        Image img = new Image( terrainFeaturesTilemap.instance.texture );
        img.frame(uv);
        return img;
    }

	public void growPlant( final int pos ){
		final Image plant = tile( pos, map[pos] );
		if (plant == null) return;
		
		plant.origin.set( 8, 12 );
		plant.scale.set( 0 );
		plant.point( DungeonTilemap.tileToWorld( pos ) );

		parent.add( plant );

		parent.add( new ScaleTweener( plant, new PointF(1, 1), 0.2f ) {
			protected void onComplete() {
				plant.killAndErase();
				killAndErase();
				updateMapCell(pos);
			}
		} );
	}

	@Override
	protected boolean needsRender(int pos) {
		return data[pos] != -1;
	}
}
