package com.regicide.board;

import java.util.ArrayList;
import java.util.List;

import com.regicide.fight.ActionType;
import com.regicide.fight.DamageType;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;

// Observer pattern
public class BoardEventManager {
    private final List<BoardObserver> observers;

    public BoardEventManager() {
        observers = new ArrayList<>();
    }

    public void addObserver(BoardObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(BoardObserver obs) {
        observers.remove(obs);
    }

    public void notifyTilePositionAttacked(Piece actor, TilePosition pos, DamageType damage, float value) {
        for (BoardObserver obs : observers) {
            obs.tilePositionAttacked(actor, pos, damage, value);
        }
    }

    public void notifyPieceDied(Piece piece) {
        for (BoardObserver obs : observers) {
            obs.pieceDied(piece);
        }
    }

    public void notifyPlayerAction(TilePosition origin, TilePosition target, ActionType action) {
        for (BoardObserver obs : observers) {
            obs.playerAction(origin, target, action);
        }
    }

    public void notifyPlayerDamage(Player player, DamageType damage, float value) {
        for (BoardObserver obs : observers) {
            obs.playerDamage(player, damage, value);
        }
    }

    public void notifyEnemyJustMoved() {
        for (BoardObserver obs : observers) {
            obs.enemiesJustMoved();
        }
    }
}
