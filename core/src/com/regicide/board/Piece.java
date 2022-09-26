package com.regicide.board;

import com.regicide.entity.Entity;
import com.regicide.gamestate.GameplayGameState;

public abstract class Piece extends Entity {
    // position on the grid
    private int x;
    private int y;

    public Piece(GameplayGameState gs) {
        super(gs);
    }
}
