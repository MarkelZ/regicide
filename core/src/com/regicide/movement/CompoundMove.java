package com.regicide.movement;

import java.util.Collection;

import com.regicide.board.Board;

/**
 * This move pattern is the composition of the patterns given in the list
 * 'movePatterns'.
 * 
 * For example, the movement of the queen in chess is the composition of
 * the rook's move pattern and the bishop's.
 */
public class CompoundMove extends MovePattern {
    // List of move patterns
    private Collection<MovePattern> movePatterns;

    public CompoundMove(Collection<MovePattern> movePatterns) {
        this.movePatterns = movePatterns;
    }

    // Compose all move patterns. Moves are composed with the 'or' operation.
    @Override
    public MoveList getMoves(Board board, int i, int j) {
        MoveList moveList = new MoveList();

        for (MovePattern mp : movePatterns) {
            moveList.add(mp.getMoves(board, i, j));
        }

        return moveList;
    }
}
