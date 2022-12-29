package com.regicide.item.items;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.regicide.board.Board;
import com.regicide.fight.ActionType;
import com.regicide.item.Item;
import com.regicide.movement.KingPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public class AutoDash extends Item {
    // Attack pattern
    private static KingPattern pattern;

    // Error threshold
    private static float TH = 1e-5f;

    public AutoDash(GameplayScene gs) {
        super(gs);

        // Initialize pattern if uninitialized
        if (pattern == null) {
            pattern = new KingPattern();
        }
    }

    @Override
    public void playerAction(TilePosition origin, TilePosition target, ActionType action) {
        // Find the direction in which the player moved
        Vector2 direction = new Vector2(target.i - origin.i, target.j - origin.j);

        // Find the closest match for the direction
        List<TilePosition> closest = new ArrayList<>();
        float mindist2 = Float.MAX_VALUE - TH - 1.f;

        for (TilePosition pos : pattern.getLeapPattern()) {
            float dist2 = direction.dst2(pos.i, pos.j);

            // If pos is closer than closest, set new closest
            if (dist2 + TH < mindist2) {
                closest.clear();
                closest.add(pos);
            }
            // Otherwise, if pos is close enough, include it in the closest
            else if (dist2 <= mindist2 + TH) {
                closest.add(pos);
            }
        }

        // Check if that tile can be attacked
        Board board = gs.getBoard();
        MoveList moves = pattern.getMoves(board, target.i, target.j);
        System.out.println(moves.canAttack);
        for (TilePosition pos : closest) {
            TilePosition attackedPos = new TilePosition(pos.i + target.i, pos.j + target.j);
            System.out.println(attackedPos);
            if (TilePosition.listContains(moves.canAttack, attackedPos)) {
                // TODO: have the player attack the enemy in the position
                System.out.println("AutoDash attacks tile position: ");
                System.out.println(pos);
            }
        }
    }
}
