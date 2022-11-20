package com.regicide.movement;

import com.regicide.board.Board;

public class KingPattern extends MovePattern {

    TilePosition[] positions = {
            new TilePosition(1, -1),
            new TilePosition(1, 0),
            new TilePosition(1, 1),
            new TilePosition(0, 1),
            new TilePosition(-1, 1),
            new TilePosition(-1, 0),
            new TilePosition(-1, -1),
            new TilePosition(0, -1) };

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        return getHopperMoves(board, i, j, positions);
    }

}
