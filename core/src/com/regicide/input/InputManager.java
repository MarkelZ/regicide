package com.regicide.input;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputManager {
    public enum Action {
        Up,
        Down,
        Left,
        Right,
        Move,
        Dash,
        Active,
        Inventory
    }

    private static HashMap<Action, ArrayList<Integer>> actionKeys;

    public static void initialize() {
        // Create hashmap with lists of keys
        actionKeys = new HashMap<>();
        for (Action action : Action.values()) {
            actionKeys.put(action, new ArrayList<Integer>());
        }

        // TODO: Load actions from config file
        actionKeys.get(Action.Up).add(Keys.W);
        actionKeys.get(Action.Up).add(Keys.UP);

        actionKeys.get(Action.Down).add(Keys.S);
        actionKeys.get(Action.Down).add(Keys.DOWN);

        actionKeys.get(Action.Right).add(Keys.D);
        actionKeys.get(Action.Right).add(Keys.RIGHT);

        actionKeys.get(Action.Left).add(Keys.A);
        actionKeys.get(Action.Left).add(Keys.LEFT);

        actionKeys.get(Action.Move).add(Keys.SPACE);

        actionKeys.get(Action.Dash).add(Keys.R);

        actionKeys.get(Action.Active).add(Keys.F);

        actionKeys.get(Action.Inventory).add(Keys.E);
    }

    public static boolean isActionPressed(Action action) {
        for (Integer key : actionKeys.get(action))
            if (Gdx.input.isKeyPressed(key))
                return true;
        return false;
    }

    public static boolean isActionJustPressed(Action action) {
        for (Integer key : actionKeys.get(action))
            if (Gdx.input.isKeyJustPressed(key))
                return true;
        return false;
    }
}
