package com.regicide.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Animation {
    // Whether the animation is running
    protected boolean isEnabled;

    // Whether the animation should be displayed
    protected boolean isVisible;

    // Position of the animation
    protected Vector2 position;

    // Update the animation
    public abstract void update(float tdelta);

    // Draw the animation
    public abstract void draw(SpriteBatch batch);

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        position = new Vector2(x, y);
    }

}
