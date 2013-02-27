package com.kingx.dungeons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kingx.dungeons.App;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "dungeon";
        cfg.useGL20 = true;
        cfg.width = 300;
        cfg.height = 200;

        new LwjglApplication(new App(), cfg);
    }
}
