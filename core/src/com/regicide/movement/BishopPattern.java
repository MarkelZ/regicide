package com.regicide.movement;

import com.regicide.board.Board;

public class BishopPattern extends MovePattern {

    @Override
    public MoveList getMoves(Board board, int i, int j) {
        MoveList result = new MoveList();

        // Top-Right
        int indi = i + 1;
        int indj = j + 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, indi, indj, result))
                break;

            // Move one step to the top-right
            indi++;
            indj++;
        }

        // Bottom-right
        indi = i + 1;
        indj = j - 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, indi, indj, result))
                break;

            // Move one step to the bottom-right
            indi++;
            indj--;
        }

        // Top-left
        indi = i - 1;
        indj = j + 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, indi, indj, result))
                break;

            // Move one step to the bottom-right
            indi--;
            indj++;
        }

        // Bottom-left
        indi = i - 1;
        indj = j - 1;
        while (true) {
            // If a collision happened, stop
            if (checkCollision(board, indi, indj, result))
                break;

            // Move one step to the bottom-right
            indi--;
            indj--;
        }

        return result;
    }

}
