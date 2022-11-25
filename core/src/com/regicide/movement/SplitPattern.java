package com.regicide.movement;

import com.regicide.board.Board;

public class SplitPattern extends MovePattern {

    protected MovePattern movePattern;
    protected MovePattern attackPattern;

    public SplitPattern(MovePattern movePattern, MovePattern attackPattern) {
        this.movePattern = movePattern;
        this.attackPattern = attackPattern;
    }

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        MoveList result = new MoveList();

        result.canMoveTo = movePattern.getMoves(board, i, j).canMoveTo;
        result.canAttack = attackPattern.getMoves(board, i, j).canAttack;

        return result;
    }

}
