package com.regicide.entity;

import com.regicide.IUpdatableDrawable;
import com.regicide.gamestate.*;

public abstract class Entity implements IUpdatableDrawable {
    protected GameplayGameState gs;

    public Entity(GameplayGameState gameState) {
        gs = gameState;
    }
}
