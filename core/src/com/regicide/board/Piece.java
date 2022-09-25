package com.regicide.board;

import com.regicide.entity.Entity;
import com.regicide.gamestate.GameplayGameState;

public abstract class Piece extends Entity {

    public Piece(GameplayGameState gs) {
        super(gs);
    }
}
