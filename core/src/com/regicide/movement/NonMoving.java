package com.regicide.movement;

import com.regicide.board.Board;

public class NonMoving extends MovePattern {

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return new MoveList();
    }

}
