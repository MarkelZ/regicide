package com.regicide.board;

import com.regicide.fight.ActionType;
import com.regicide.fight.DamageType;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;

public interface BoardObserver {
    // Piece actor attacked tileposition pos with damage type damage
    public void tilePositionAttacked(Piece actor, TilePosition pos, DamageType damage, float value);

    // Death of a piece
    public void pieceDied(Piece piece);

    // Player sitting at origin did action towards target
    public void playerAction(TilePosition origin, TilePosition target, ActionType action);

    // Player took damage
    public void playerDamage(Player player, DamageType damage, float value);
}
