package com.regicide.movement;

import com.regicide.board.Board;

public abstract class MovePattern {
    public abstract MoveList getMoves(Board board, int i, int j);

    protected boolean isWithinBounds(Board board, int i, int j) {
        return i >= 0 && i < board.width && j >= 0 && j < board.height;
    }
}
