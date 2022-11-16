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
        MoveList result = new MoveList();

        for (TilePosition pos : positions) {
            int ni = i + pos.i;
            int nj = j + pos.j;

            if (isWithinBounds(board, ni, nj) && board.pieceGrid[ni][nj] == null) {
                result.canMoveTo.add(new TilePosition(ni, nj));
            }
        }

        return result;
    }

}
