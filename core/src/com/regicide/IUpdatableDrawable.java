package com.regicide;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IUpdatableDrawable {
    /**
     * Update game object for one game tick.
     * 
     * @param tdelta Time between update calls, in seconds per tick.
     */
    public void update(float tdelta);

    /**
     * Draw game object.
     * 
     * @param batch SpriteBatch used to draw the object.
     */
    public void draw(SpriteBatch batch);
}
