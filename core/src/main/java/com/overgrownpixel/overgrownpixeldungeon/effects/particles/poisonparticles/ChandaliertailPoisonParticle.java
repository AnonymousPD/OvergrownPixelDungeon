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

package com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles;

import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.Emitter.Factory;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class ChandaliertailPoisonParticle extends PixelParticle {

    public static final Factory FACTORY = new Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((ChandaliertailPoisonParticle)emitter.recycle( ChandaliertailPoisonParticle.class )).reset( x, y );
        }
        @Override
        public boolean lightMode() {
            return true;
        };
    };

    public ChandaliertailPoisonParticle() {
        size(2.0f);
        color(0xDDAC14);
        this.acc.set(0.0f, 50.0f);
    }

    public void reset(float f, float f2) {
        revive();
        this.x = f;
        this.y = f2;
        f = Random.Float(0.5f, 1.0f);
        this.lifespan = f;
        this.left = f;
        this.speed.polar(-Random.Float(3.1415925f), Random.Float(20.0f, 40.0f));
    }

    public void update() {
        super.update();
        size(Random.Float((5.0f * this.left) / this.lifespan));
    }
}
