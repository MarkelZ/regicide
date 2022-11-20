package com.regicide.movement;

import com.regicide.board.Board;

public class RookPattern extends MovePattern {

    TilePosition[] ridePattern = {
            new TilePosition(1, 0),
            new TilePosition(-1, 0),
            new TilePosition(0, 1),
            new TilePosition(0, -1)
    };

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return getRiderMoves(board, i, j, ridePattern);
    }

}
