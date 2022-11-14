package com.regicide.gamestate;

import com.regicide.Game;
import com.regicide.IUpdatableDrawable;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState implements IUpdatableDrawable {
    protected OrthographicCamera camera;
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    /**
     * Update gamestate.
     * 
     * @param tdelta Time delta (seconds per tick).
     */
    public abstract void update(float tdelta);

    /**
     * Draw gamestate.
     */
    public abstract void draw(SpriteBatch batch);
}
