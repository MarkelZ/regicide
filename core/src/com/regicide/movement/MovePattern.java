package com.regicide.movement;

import com.regicide.board.Board;

public abstract class MovePattern {
    public abstract MoveList getMoves(Board board, int i, int j);

    // Whether if the piece has collided with either the board or another piece
    // If there is a piece collision, the collided piece is added to result
    // If there is no collision, the tile is added to tiles we can move to
    protected static boolean checkCollision(Board board, int i, int j, MoveList result) {
        // If the edge of the board is hit, return true
        if (!board.isWithinBounds(i, j)) {
            return true;
        }

        // If a piece is hit, add the piece's tile to attacked tiles and return true
        if (board.pieceGrid[i][j] != null) {
            result.canAttack.add(new TilePosition(i, j));
            return true;
        }

        // If collision happened, add tile to tiles we can move to and return false
        result.canMoveTo.add(new TilePosition(i, j));
        return false;
    }

    // Gets the valid moves of a leaper type piece
    protected static MoveList getLeaperMoves(Board board, int i, int j, TilePosition[] leapPattern) {
        MoveList result = new MoveList();

        for (TilePosition pos : leapPattern) {
            int ni = i + pos.i;
            int nj = j + pos.j;

            checkCollision(board, ni, nj, result);
        }

        return result;
    }

    protected static MoveList getRiderMoves(Board board, int i, int j, TilePosition[] ridePattern) {
        MoveList result = new MoveList();

        int indi, indj;

        // Perform a 'ride' for each direction the piece can move
        for (TilePosition pos : ridePattern) {
            // Start by moving just one tile in the given direction
            indi = i + pos.i;
            indj = j + pos.j;

            while (true) {
                // If a collision happens, stop sliding
                if (checkCollision(board, indi, indj, result))
                    break;

                // Move one step
                indi += pos.i;
                indj += pos.j;
            }
        }

        return result;
    }
}
