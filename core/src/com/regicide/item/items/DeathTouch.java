package com.regicide.item.items;

import java.util.ArrayList;

import com.regicide.board.Board;
import com.regicide.board.Piece;
import com.regicide.board.Piece.Kind;
import com.regicide.fight.ActionType;
import com.regicide.fight.DamageType;
import com.regicide.item.Item;
import com.regicide.movement.KingPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public class DeathTouch extends Item {
    // List of pieces touched in the previous turn
    protected ArrayList<Piece> prevTouchedPieces;

    // Touch pattern
    private static KingPattern touchPattern;

    public DeathTouch(GameplayScene gs) {
        super(gs);

        prevTouchedPieces = new ArrayList<>();

        // Initialize pattern if uninitialized
        if (touchPattern == null) {
            touchPattern = new KingPattern();
        }
    }

    @Override
    public void enemiesJustMoved() {
        prevTouchedPieces = getTouchedPieces();
    }

    @Override
    public void playerAction(TilePosition origin, TilePosition target, ActionType action) {
        // Get pieces currently touched
        ArrayList<Piece> touchedPieces = getTouchedPieces();

        // If a piece is touched both currently and before the move, attack it
        Board board = gs.getBoard();
        Player player = board.getPlayer();
        for (Piece p : touchedPieces) {
            if (prevTouchedPieces.contains(p)) {
                System.out.println("Death touch attacks piece at: ");
                System.out.println(p.boardPos);
                player.animatePieceAttack(p.boardPos, 20);
                board.attackPiece(player, p.boardPos, DamageType.Melee, player.getStats().attackDamage);
            }
        }
    }

    protected ArrayList<Piece> getTouchedPieces() {
        Board board = gs.getBoard();
        Player player = board.getPlayer();
        MoveList moves = touchPattern.getMoves(board, player.boardPos.i, player.boardPos.j);

        // List of pieces the player is touching
        ArrayList<Piece> touchedPieces = new ArrayList<>();

        for (TilePosition pos : moves.canAttack) {
            // If piece is hostile, add to touched pieces
            Piece p = board.getPiece(pos);
            if (p.getKind() == Kind.Hostile) {
                touchedPieces.add(p);
            }
        }

        return touchedPieces;
    }
}
