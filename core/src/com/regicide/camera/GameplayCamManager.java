package com.regicide.camera;

import com.badlogic.gdx.graphics.Camera;
import com.regicide.input.InputManager;
import com.regicide.input.InputManager.Action;

public class GameplayCamManager extends CameraManager {

    public GameplayCamManager(Camera camera) {
        super(camera);
    }

    @Override
    public void update(float tdelta) {
        if (InputManager.isActionPressed(Action.Up))
            camera.translate(0, 2, 0);
        if (InputManager.isActionPressed(Action.Down))
            camera.translate(0, -2, 0);
        if (InputManager.isActionPressed(Action.Right))
            camera.translate(2, 0, 0);
        if (InputManager.isActionPressed(Action.Left))
            camera.translate(-2, 0, 0);

        super.update(tdelta);
    }
}
