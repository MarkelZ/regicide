package com.regicide.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;

public class GameplayCamManager extends CameraManager {

    public GameplayCamManager(Camera camera) {
        super(camera);
    }

    @Override
    public void update(float tdelta) {
        if (Gdx.input.isKeyPressed(Keys.W))
            camera.translate(0, 2, 0);
        if (Gdx.input.isKeyPressed(Keys.S))
            camera.translate(0, -2, 0);
        if (Gdx.input.isKeyPressed(Keys.D))
            camera.translate(2, 0, 0);
        if (Gdx.input.isKeyPressed(Keys.A))
            camera.translate(-2, 0, 0);

        super.update(tdelta);
    }
}
