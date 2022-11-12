package com.regicide.gamestate;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameState {
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Viewport viewport;

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
