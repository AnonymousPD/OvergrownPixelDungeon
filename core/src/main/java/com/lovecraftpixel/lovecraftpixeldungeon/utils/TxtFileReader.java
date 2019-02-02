package com.lovecraftpixel.lovecraftpixeldungeon.utils;

import android.content.Context;

import com.lovecraftpixel.lovecraftpixeldungeon.Assets;
import com.lovecraftpixel.lovecraftpixeldungeon.LovecraftPixelDungeon;
import com.lovecraftpixel.lovecraftpixeldungeon.actors.hero.Hero;
import com.lovecraftpixel.lovecraftpixeldungeon.messages.Messages;
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
            LovecraftPixelDungeon.reportException(e);
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
            LovecraftPixelDungeon.reportException(e);
            return "Error";
        }
    }
}
