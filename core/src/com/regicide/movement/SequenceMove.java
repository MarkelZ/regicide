package com.regicide.movement;

import java.util.Collection;

import com.regicide.board.Board;

/**
 * This move pattern takes a list of move patterns, and
 * simulates performing all move patterns in the list one-by-one.
 * 
 * For example, if the list of move patterns is two knight move patterns,
 * then this move pattern will be like doing two knight moves in a row.
 */
public class SequenceMove extends MovePattern {
    // List of move patterns
    private Collection<MovePattern> movePatterns;

    public SequenceMove(Collection<MovePattern> movePatterns) {
        this.movePatterns = movePatterns;
    }

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        // To where we are going to move
        MoveList nextPositions = new MoveList();

        // From where we are going to move
        MoveList prevPositions = new MoveList();
        prevPositions.canMoveTo.add(new TilePosition(i, j));

        // For each move pattern we have, we are going to get all moves from each of the
        // 'previous positions', we will add those moves to the 'next positions', and
        // finally we are going to start from those next positions in for the next move
        // pattern
        for (MovePattern mp : movePatterns) {
            nextPositions.clear();
            for (TilePosition pos : prevPositions.canMoveTo) {
                nextPositions.add(mp.getMoves(board, pos.i, pos.j));
            }
            prevPositions = nextPositions;
        }

        return nextPositions;
    }
}
