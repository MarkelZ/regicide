package com.regicide.movement;

import com.regicide.board.Board;

public class BishopPattern extends MovePattern {

    TilePosition[] ridePattern = {
            new TilePosition(1, 1),
            new TilePosition(1, -1),
            new TilePosition(-1, 1),
            new TilePosition(-1, -1)
    };

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return getRiderMoves(board, i, j, ridePattern);
    }

}
