package com.regicide.gamestate;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameState {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    /**
     * Update gamestate.
     * 
     * @param tdelta Time delta (seconds per tick).
     */
    public abstract void update(float tdelta);

    /**
     * Draw gamestate.
     */
    public abstract void draw();
}
