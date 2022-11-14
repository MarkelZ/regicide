package com.regicide.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteAnimation {
    // Texture of the animaiton
    protected Texture animationSprite;

    // Index of the current frame
    protected int frameIndex;

    // Index of the selected animation
    protected int animationIndex;

    // Width and height of each frame
    protected int frameWidth;
    protected int frameHeight;

    // Maximum values + 1 of frame index and animation index
    // That is, the indices should be lower than these numbers
    protected int maxframeIndex;
    protected int maxAnimationIndex;

    // Whether the animation is running
    protected boolean isEnabled;

    // Whether the animation should be displayed
    protected boolean isVisible;

    // How many game ticks it takes to switch to the next frame
    protected int tickRate;

    // Tick counter which resets back to 0 when it reaches tickRate
    protected int tickCount;

    public SpriteAnimation(Texture animationSprite, int frameWidth, int frameHeight, int tickRate) {
        this.animationSprite = animationSprite;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        int width = animationSprite.getWidth();
        int height = animationSprite.getHeight();
        maxframeIndex = width / frameWidth;
        maxAnimationIndex = height / frameHeight;
        frameIndex = 0;
        animationIndex = 0;

        setTickRate(tickRate);
        tickCount = 0;

        isEnabled = true;
        isVisible = true;
    }

    public void update(float tdelta) {
        // If animation disabled, do nothing
        if (!isEnabled)
            return;

        // Increment tick count and update frameIndex when tickRate reached
        tickCount++;
        if (tickCount >= tickRate) {
            tickCount = 0;
            frameIndex++;
            if (frameIndex >= maxframeIndex) {
                frameIndex = 0;
            }
        }
    }

    public void draw(SpriteBatch batch, float x, float y) {
        // If not visible, do nothing
        if (!isVisible)
            return;

        // Draw the rectangular segment of sprite corresponding to the frame
        batch.draw(animationSprite, x, y,
                frameIndex * frameWidth, animationIndex * frameHeight,
                frameWidth, frameHeight);
    }

    public void setAnimationIndex(int ai) {
        if (ai >= 0 && ai < maxAnimationIndex) {
            animationIndex = ai;
        }
    }

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

    public int getTickRate() {
        return tickRate;
    }

    public void setTickRate(int tickRate) {
        if (tickRate < 1) {
            this.tickRate = 1;
        } else {
            this.tickRate = tickRate;
        }
    }
}
