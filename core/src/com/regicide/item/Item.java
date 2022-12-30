package com.regicide.item;

import com.badlogic.gdx.graphics.Texture;
import com.regicide.board.BoardObserver;
import com.regicide.board.Piece;
import com.regicide.fight.ActionType;
import com.regicide.fight.DamageType;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public abstract class Item implements BoardObserver {
    protected Texture sprite;
    protected final GameplayScene gs;

    public Item(GameplayScene gs) {
        this.gs = gs;
    }

    public void update(float tdelta) {
        // Default to no op
    }

    @Override
    public void tilePositionAttacked(Piece actor, TilePosition pos, DamageType damage, float value) {
        // Default to no op
    }

    @Override
    public void pieceDied(Piece piece) {
        // Default to no op
    }

    @Override
    public void playerAction(TilePosition origin, TilePosition target, ActionType action) {
        // Default to no op
    }

    @Override
    public void playerDamage(Player player, DamageType damage, float value) {
        // Default to no op
    }

    @Override
    public void enemiesJustMoved() {
        // Default to no op
    }

}
