package com.regicide.camera;

import com.badlogic.gdx.graphics.Camera;

public abstract class CameraManager {
    protected Camera camera;

    public CameraManager(Camera camera) {
        this.camera = camera;
    }

    public void update(float tdelta) {
        camera.update();
    }
}
