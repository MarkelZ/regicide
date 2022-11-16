package com.regicide.board;

import com.regicide.IUpdatableDrawable;
import com.regicide.gamestate.GameplayGameState;
import com.regicide.movement.MovePattern;

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
    protected GameplayGameState gs;

    // Position on the grid
    protected int i;
    protected int j;

    // World position
    protected float x;
    protected float y;

    // Kind of the piece
    protected Kind kind;

    // Movement pattern
    protected MovePattern movePattern;

    public Piece(GameplayGameState gs, Kind kind, MovePattern movePattern) {
        this.gs = gs;
        this.kind = kind;
        this.movePattern = movePattern;
    }
}
