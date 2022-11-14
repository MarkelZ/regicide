package com.regicide.animation;

import java.util.ArrayList;

// Synchronize all sprites
public class AnimationManager {

    protected float animationTime;
    protected ArrayList<ISyncAnimation> animations;

    public AnimationManager() {
        animationTime = 0.f;
        animations = new ArrayList<>();
    }

    public void update(float tdelta) {
        // if (true) { // tick cond
        // for (Animation a : animations) {
        // a.tick();
        // }
        // }
    }
}
