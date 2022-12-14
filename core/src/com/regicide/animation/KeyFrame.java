package com.regicide.animation;

import com.badlogic.gdx.math.Vector2;

public class KeyFrame {
    public KeyFrame(Vector2 key, int frame) {
        this.key = key;
        this.frame = frame;
    }

    public Vector2 key;
    public int frame;
}
