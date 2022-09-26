package com.regicide.entity;

import com.regicide.IUpdatableDrawable;
import com.regicide.gamestate.*;

public abstract class Entity implements IUpdatableDrawable {
    private GameplayGameState gs;

    public Entity(GameplayGameState gameState) {
        gs = gameState;
    }
}
