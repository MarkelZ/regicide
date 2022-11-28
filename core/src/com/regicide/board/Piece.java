package com.regicide.board;

import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.movement.MoveList;
import com.regicide.movement.MovePattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public abstract class Piece implements IUpdatableDrawable {
    /**
     * How the piece interacts with other pieces.
     */
    public enum Kind {
        /**
         * The player or an ally of the player.
         */
        Friendly,
        /**
         * It is not an ally, but it is not hostile either
         */
        Neutral,
        /**
         * It attacks the player and their allies.
         */
        Hostile,
        /**
         * It cannot be interacted with.
         */
        None
    }

    // Game state to which the piece belongs
    protected GameplayScene gs;
    protected Board board;

    // Position on the grid
    public TilePosition boardPos;
    protected TilePosition nextPos;

    // World position
    protected Vector2 worldPos;

    // Kind of the piece
    protected Kind kind;

    // Movement pattern
    protected MovePattern movePattern;

    public Piece(GameplayScene gs, Kind kind, MovePattern movePattern, TilePosition pos) {
        this.gs = gs;
        board = gs.getBoard();
        this.kind = kind;
        this.movePattern = movePattern;
        boardPos = pos;
        this.moveTo(pos);
    }

    public MoveList getMoves() {
        return movePattern.getMoves(board, boardPos.i, boardPos.j);
    }

    public void moveTo(TilePosition pos) {
        Piece[][] grid = board.pieceGrid;
        grid[boardPos.i][boardPos.j] = null;
        grid[pos.i][pos.j] = this;
        boardPos = pos;
        worldPos = board.boardIndicesToWorldCoords(pos);
    }

    public void calculateNextPos() {
        nextPos = new TilePosition(boardPos.i, boardPos.j);
    }

    public void moveToNextPos() {
        moveTo(nextPos);
        nextPos = null;
    }

    public Kind getKind() {
        return kind;
    }
}
