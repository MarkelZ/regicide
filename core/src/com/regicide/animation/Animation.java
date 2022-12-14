package com.regicide.animation;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Animation {
    // Whether the animation is running
    protected boolean isEnabled;

    // Whether the animation should be displayed
    protected boolean isVisible;

    // Position of the animation
    protected Vector2 position;

    // TODO: Put the interpolation code in an interpolator object

    // Whether the animation is interpolating
    protected boolean isInterpolating;

    // The positions to interpolate between
    protected Vector2 interpOrigin;
    protected Vector2 interpTarget;
    protected List<KeyFrame> keyFrames;

    // Timer for interpolation
    protected int interpTimer;

    // Duration of the interpolation
    protected int interpDuration;

    // Update the animation
    public void update(float tdelta) {
        if (!isEnabled)
            return;

        if (isInterpolating) {
            interpTimer--;
            if (interpTimer <= 0) {
                position = new Vector2(interpTarget);
                if (keyFrames.isEmpty()) {
                    isInterpolating = false;
                } else {
                    startInterpNextFrame();
                }
            } else {
                float alpha = (float) interpTimer / (float) interpDuration;
                Vector2 origscl = new Vector2(interpOrigin);
                Vector2 targscl = new Vector2(interpTarget);
                origscl.scl(alpha);
                targscl.scl(1 - alpha);
                this.position = origscl.add(targscl);
            }
        }
    }

    // Draw the animation
    public abstract void draw(SpriteBatch batch);

    public void interpolate(List<KeyFrame> otherFrames) {
        if (otherFrames.isEmpty())
            return;
        this.keyFrames = otherFrames;
        startInterpNextFrame();
    }

    private void startInterpNextFrame() {
        KeyFrame kf = keyFrames.get(0);
        keyFrames.remove(0);
        interpolate(kf.key, kf.frame);
    }

    // Interpolate from current position to the target position
    // given the interpolation duration in game ticks
    protected void interpolate(Vector2 target, int duration) {
        interpOrigin = new Vector2(position);
        interpTarget = target;
        isInterpolating = true;
        interpTimer = duration;
        interpDuration = duration;
    }

    // Interpolate from current position to the target position
    // given the interpolation speed in pixels per game tick
    protected void interpolate(Vector2 target, float speed) {
        int duration = (int) (position.dst(target) / speed);
        interpolate(target, duration);
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

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        position = new Vector2(x, y);
    }

    public boolean isInterpolating() {
        return isInterpolating;
    }

}
