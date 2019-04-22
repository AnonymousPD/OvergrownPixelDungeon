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

package com.overgrownpixel.overgrownpixeldungeon.utils;

import android.content.Context;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.watabou.noosa.Game;
import com.watabou.utils.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class TxtFileReader {
    private String fileName;
    ArrayList<String> texts = new ArrayList();

    public TxtFileReader(String path, Context context) {
        this.fileName = path;
        try {
            refresh(context);
        } catch (Exception e) {
            OvergrownPixelDungeon.reportException(e);
        }
    }

    public void refresh(Context context) throws IOException {
        Reader inputStreamReader = new InputStreamReader(context.getAssets().open(this.fileName, 1));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = "";
        while (str != null) {
            str = bufferedReader.readLine();
            if (!(str == null || str.equals("") || str.charAt(0) != '-')) {
                this.texts.add(str.substring(1));
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    public String composeBook() {
        String str = this.texts.get(Random.Int(this.texts.size() - 1));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Messages.get(Hero.class, "foundbook"));
        stringBuilder.append(" ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String getRandomBookTitle() {
        try {
            return new TxtFileReader(Assets.BOOKTITLES, Game.instance).composeBook();
        } catch (Exception e) {
            OvergrownPixelDungeon.reportException(e);
            return "Error";
        }
    }
}
