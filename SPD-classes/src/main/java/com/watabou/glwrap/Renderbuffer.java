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

package com.watabou.glwrap;

import android.opengl.GLES20;

public class Renderbuffer {

	public static final int RGBA8		= GLES20.GL_RGBA;	// ?
	public static final int DEPTH16		= GLES20.GL_DEPTH_COMPONENT16;
	public static final int STENCIL8	= GLES20.GL_STENCIL_INDEX8;
	
	private int id;
	
	public Renderbuffer() {
		int[] buffers = new int[1];
		GLES20.glGenRenderbuffers( 1, buffers, 0 );
		id = buffers[0];
	}
	
	public int id() {
		return id;
	}
	
	public void bind() {
		GLES20.glBindRenderbuffer( GLES20.GL_RENDERBUFFER, id );
	}
	
	public void delete() {
		int[] buffers = {id};
		GLES20.glDeleteRenderbuffers( 1, buffers, 0 );
	}
	
	public void storage( int format, int width, int height ) {
		GLES20.glRenderbufferStorage( GLES20.GL_RENDERBUFFER, format , width, height );
	}
}
