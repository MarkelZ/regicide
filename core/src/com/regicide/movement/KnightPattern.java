package com.regicide.movement;

import com.regicide.board.Board;

public class KnightPattern extends MovePattern {

    TilePosition[] positions = {
            new TilePosition(2, 1),
            new TilePosition(2, -1),
            new TilePosition(1, 2),
            new TilePosition(1, -2),
            new TilePosition(-2, 1),
            new TilePosition(-2, -1),
            new TilePosition(-1, 2),
            new TilePosition(-1, -2) };

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return getHopperMoves(board, i, j, positions);
    }

}
