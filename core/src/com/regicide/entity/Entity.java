package com.regicide.entity;

import com.regicide.gamestate.*;

public abstract class Entity {
    private GameplayGameState gs;

    public Entity(GameplayGameState gameState) {
        gs = gameState;
    }

    public abstract void update(float tdelta);

    public abstract void draw();
}
