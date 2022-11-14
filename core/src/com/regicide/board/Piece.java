package com.regicide.board;

import com.regicide.IUpdatableDrawable;
import com.regicide.gamestate.GameplayGameState;

public abstract class Piece implements IUpdatableDrawable {
    protected GameplayGameState gs;

    // position on the grid
    protected int i;
    protected int j;

    // world position
    protected float x;
    protected float y;

    public Piece(GameplayGameState gs) {
        this.gs = gs;
    }
}
