package com.regicide.board;

import com.regicide.entity.Entity;
import com.regicide.gamestate.GameplayGameState;

public abstract class Piece extends Entity {
    // position on the grid
    protected int i;
    protected int j;

    // world position
    protected float x;
    protected float y;

    public Piece(GameplayGameState gs) {
        super(gs);
    }
}
