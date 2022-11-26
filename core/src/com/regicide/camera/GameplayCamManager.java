package com.regicide.camera;

import com.badlogic.gdx.graphics.Camera;
import com.regicide.input.Input;
import com.regicide.input.Input.Action;

public class GameplayCamManager extends CameraManager {

    public GameplayCamManager(Camera camera) {
        super(camera);
    }

    @Override
    public void update(float tdelta) {
        if (Input.isActionPressed(Action.Up))
            camera.translate(0, 2, 0);
        if (Input.isActionPressed(Action.Down))
            camera.translate(0, -2, 0);
        if (Input.isActionPressed(Action.Right))
            camera.translate(2, 0, 0);
        if (Input.isActionPressed(Action.Left))
            camera.translate(-2, 0, 0);

        super.update(tdelta);
    }
}
