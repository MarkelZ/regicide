package com.regicide.scene;

import com.regicide.Game;
import com.regicide.IUpdatableDrawable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene implements IUpdatableDrawable {
    protected Game game;

    public Scene(Game game) {
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
