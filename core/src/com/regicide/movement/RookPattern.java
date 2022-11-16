package com.regicide.movement;

import com.regicide.board.Board;

public class RookPattern extends MovePattern {

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        MoveList result = new MoveList();

        // Right
        int ind = i + 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, ind, j, result))
                break;

            // Move one step to the right
            ind++;
        }

        // Left
        ind = i - 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, ind, j, result))
                break;

            // Move one step to the right
            ind--;
        }

        // Up
        ind = j + 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, i, ind, result))
                break;

            // Move one step to the right
            ind++;
        }

        // Down
        ind = j - 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, i, ind, result))
                break;

            // Move one step to the right
            ind--;
        }

        return result;
    }

}
