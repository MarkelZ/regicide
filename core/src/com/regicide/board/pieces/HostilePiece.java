package com.regicide.board.pieces;

import com.regicide.board.Piece;
import com.regicide.movement.MovePattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public abstract class HostilePiece extends Piece {

    public HostilePiece(GameplayScene gs, MovePattern pattern, TilePosition pos) {
        super(gs, Kind.Hostile, pattern, pos);
    }

    // TODO: All hostile pieces should be of this type.
    // There should be code for finding the closest movable tile to player and stuff
    // like that
}
