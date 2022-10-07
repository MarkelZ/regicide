package com.regicide.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.IUpdatableDrawable;

public class Room implements IUpdatableDrawable {
    public final int WIDTH;
    public final int HEIGHT;
    public final int WIDTH_HALF;
    public final int HEIGHT_HALF;

    private static Texture tilesSprite;
    private static int TILES_SPRITE_SIZE = 32;

    private boolean isOpen;

    public Room(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        WIDTH_HALF = WIDTH / 2;
        HEIGHT_HALF = HEIGHT / 2;

        if (tilesSprite == null) {
            tilesSprite = new Texture("tiles.png");
            TILES_SPRITE_SIZE = tilesSprite.getWidth();
            if (TILES_SPRITE_SIZE != tilesSprite.getHeight()) {
                System.out.println("WARNING: block sprite width does not match its height.");
            }
        }
    }

    @Override
    public void update(float tdelta) {
        // if time-out/defeated critters then open back
    }

    @Override
    public void draw(SpriteBatch batch) {
        // This is a bit hardcoded for now
        for (int i = 0; i < WIDTH_HALF; i++) {
            for (int j = 0; j < HEIGHT_HALF; j++) {
                batch.draw(tilesSprite, i * TILES_SPRITE_SIZE, j * TILES_SPRITE_SIZE);
            }
        }
    }

    public void open() {
        isOpen = true;
        // Spawn stuff from spawners
    }

    public boolean isOpen() {
        return isOpen;
    }
}
