package com.regicide.board;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.animation.KeyFrame;
import com.regicide.animation.SpriteAnimation;
import com.regicide.fight.DamageType;
import com.regicide.movement.MoveList;
import com.regicide.movement.MovePattern;
import com.regicide.movement.TilePosition;
import com.regicide.particle.BreakParticle;
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

    // Sprite animation
    protected SpriteAnimation animation;

    public Piece(GameplayScene gs, Kind kind, TilePosition boardPos) {
        this.gs = gs;
        this.kind = kind;
        this.boardPos = boardPos;
        board = gs.getBoard();

        this.moveTo(boardPos);
    }

    public void takeDamage(Piece piece, DamageType damage, float value) {

    }

    public void moveTo(TilePosition pos) {
        Piece[][] grid = board.pieceGrid;

        // DEBUG: Check that we are not moving to a tile occupied by another piece
        Piece pp = grid[pos.i][pos.j];
        if (pp != this && pp != null) {
            System.out.println("WARNING: Piece on tile " + boardPos + " is moving to tile" + pos
                    + ", but that tile is already occupied!");
        }

        grid[boardPos.i][boardPos.j] = null;
        grid[pos.i][pos.j] = this;
        boardPos = pos;
        worldPos = board.boardIndicesToWorldCoords(pos);

        if (animation != null)
            animation.setPosition(worldPos);
    }

    // Animates dashing into the given tileposition and back
    // Duration should be even, if odd then a frame may be dropped
    public void animatePieceAttack(TilePosition pos, int duration) {
        Vector2 target = board.boardIndicesToWorldCoords(pos);
        int halfduration = duration / 2;
        List<KeyFrame> keyFrames = new LinkedList<>();
        keyFrames.add(new KeyFrame(target, halfduration));
        keyFrames.add(new KeyFrame(worldPos, halfduration));
        animation.interpolate(keyFrames);
    }

    public void animateAndMoveTo(TilePosition pos, int duration) {
        Vector2 originPos = new Vector2(worldPos);
        moveTo(pos);
        Vector2 target = board.boardIndicesToWorldCoords(pos);
        List<KeyFrame> keyFrames = new LinkedList<>();
        keyFrames.add(new KeyFrame(target, duration));
        animation.setPosition(originPos);
        animation.interpolate(keyFrames);
    }

    public void calculateNextPos() {
        nextPos = new TilePosition(boardPos.i, boardPos.j);
    }

    public void moveToNextPos() {
        animateAndMoveTo(nextPos, 20);
        nextPos = null;
    }

    public void setMovePattern(MovePattern pattern) {
        this.movePattern = pattern;
    }

    public void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }

    public MoveList getMoves() {
        return movePattern.getMoves(board, boardPos.i, boardPos.j);
    }

    public Kind getKind() {
        return kind;
    }

    public Vector2 getWorldPos() {
        return worldPos;
    }

    public boolean isAnimating() {
        return animation.isInterpolating();
    }

    protected void explode(int numparticles) {
        for (int _ = 0; _ < numparticles; _++) {
            gs.addParticle(
                    new BreakParticle(gs,
                            new Vector2(worldPos.x + board.halfTileSize, worldPos.y + board.halfTileSize),
                            animation.getTexture(), 8, 8));
        }
    }

    protected void die() {
        explode(10);
        gs.getBoard().removePiece(this);
    }
}
