package com.regicide.board;

import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
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

    // Position on the grid
    protected TilePosition boardPos;
    protected TilePosition nextPos;

    // World position
    protected Vector2 worldPos;

    // Kind of the piece
    protected Kind kind;

    // Movement pattern
    protected MovePattern movePattern;

    public Piece(GameplayScene gs, Kind kind, MovePattern movePattern, TilePosition pos) {
        this.gs = gs;
        this.kind = kind;
        this.movePattern = movePattern;
        boardPos = pos;
        this.moveTo(pos);
    }

    public void moveTo(TilePosition pos) {
        Piece[][] grid = gs.getBoard().pieceGrid;
        grid[boardPos.i][boardPos.j] = null;
        grid[pos.i][pos.j] = this;
        boardPos = pos;
        worldPos = gs.getBoard().boardIndicesToWorldCoords(pos);
    }

    public void calculateNextPos() {
        nextPos = new TilePosition(boardPos.i, boardPos.j);
    }

    public void moveToNextPos() {
        // ...
        nextPos = null;
    }
}
