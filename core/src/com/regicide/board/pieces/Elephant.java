package com.regicide.board.pieces;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.board.Piece;
import com.regicide.movement.KingPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.movement.WazirPattern;
import com.regicide.scene.GameplayScene;

public class Elephant extends HostilePiece {
    protected static Texture texture; // should be added to spriteanimation

    public Elephant(GameplayScene gs, TilePosition pos) {
        super(gs, pos);

        if (texture == null) {
            texture = new Texture("pieces/elephant.png");
        }

        // initialize(new KingPattern(), texture);
        initialize(new WazirPattern(), texture);
    }

    @Override
    public void update(float tdelta) {
        super.update(tdelta);
    }

    // TODO: Put this code in hostile piece instead
    @Override
    public void calculateNextPos() {
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

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
