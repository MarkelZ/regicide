package com.regicide.movement;

import com.regicide.board.Board;

public class FerzPattern extends MovePattern {

    TilePosition[] leapPattern = {
            new TilePosition(1, -1),
            new TilePosition(1, 1),
            new TilePosition(-1, 1),
            new TilePosition(-1, -1) };

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return getLeaperMoves(board, i, j, leapPattern);
    }

}
