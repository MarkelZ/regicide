package com.regicide.item;

import com.badlogic.gdx.graphics.Texture;
import com.regicide.board.BoardObserver;
import com.regicide.board.Piece;
import com.regicide.fight.ActionType;
import com.regicide.fight.DamageType;
import com.regicide.movement.TilePosition;

public abstract class Item implements BoardObserver {
    protected Texture sprite;

    @Override
    public void tilePositionAttacked(Piece actor, TilePosition pos, DamageType damage, float value) {
        // Default to no op
    }

    @Override
    public void playerAction(TilePosition origin, TilePosition target, ActionType action) {
        // Default to no op
    }

}
