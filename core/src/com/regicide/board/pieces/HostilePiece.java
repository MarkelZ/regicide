package com.regicide.board.pieces;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.input.InputManager;
import com.regicide.input.InputManager.Action;
import com.regicide.movement.MoveList;
import com.regicide.movement.MovePattern;
import com.regicide.movement.TilePosition;
import com.regicide.particle.BreakParticle;
import com.regicide.scene.GameplayScene;

public abstract class HostilePiece extends Piece {
    public HostilePiece(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Hostile, pos);
    }

    public void initialize(MovePattern movePattern, Texture texture) {
        this.movePattern = movePattern;
        animation = new SpriteAnimation(texture, worldPos, board.tileSize, board.tileSize, 8);
    }

    @Override
    public void update(float tdelta) {
        // Update animation
        animation.update(tdelta);

        // Debug
        // Spawn some break particles
        if (InputManager.isActionJustPressed(Action.Dash)) {
            for (int _ = 0; _ < 20; _++) {
                gs.addParticle(
                        new BreakParticle(gs,
                                new Vector2(worldPos.x + board.halfTileSize, worldPos.y + board.halfTileSize),
                                animation.getTexture(), 8, 8));
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch);
    }

    // Generic method for moving to the tile closest to the player
    // If this is the desired behavior, override the 'calculateNextPos'
    // method with this one
    protected void nextPosClosestToPlayer() {
        // Legal moves
        MoveList moves = getMoves();

        // Get pieces that can be attacked
        ArrayList<Piece> attackable = new ArrayList<>();
        for (TilePosition pos : moves.canAttack) {
            Piece piece = board.getPiece(pos);
            if (piece != null) { // unnecessary check
                attackable.add(piece);
            }
        }

        // If the player can be attacked, attack it
        if (attackable.contains(board.getPlayer())) {
            // Attack player and don't move
            // TODO: Attack player
            super.calculateNextPos();
            return;
        }

        // If a friendly piece can be attacked, attack it
        for (Piece piece : attackable) {
            if (piece.getKind() == Kind.Friendly) {
                // Attack piece and don't move
                // TODO: Attack piece
                super.calculateNextPos();
                return;
            }
        }

        // If cannot attack any piece, move as close to player as possible
        // Find closest tile to player
        TilePosition playerpos = board.getPlayer().boardPos;
        TilePosition closest = null;
        int mindist2 = Integer.MAX_VALUE;
        for (TilePosition pos : moves.canMoveTo) {
            int dist2 = pos.distance2(playerpos);
            if (dist2 < mindist2) {
                closest = pos;
                mindist2 = dist2;
            }
        }

        // Move to closest tile
        if (closest != null) {
            nextPos = closest;
            // Place piece at next position so that other pieces won't overlap
            board.pieceGrid[nextPos.i][nextPos.j] = this;
            return;
        }

        // If no move is possible, stand still
        super.calculateNextPos();
    }
}
