package com.regicide.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Animation {
    // Whether the animation is running
    protected boolean isEnabled;

    // Whether the animation should be displayed
    protected boolean isVisible;

    // Update the animation
    public abstract void update(float tdelta);

    // Draw the animation
    public abstract void draw(SpriteBatch batch, float x, float y);

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

}
