package com.regicide.movement;

import java.util.ArrayList;

import com.regicide.util.ListUtils;

public class MoveList {
    public ArrayList<TilePosition> canMoveTo;
    public ArrayList<TilePosition> canAttack;

    public MoveList() {
        canMoveTo = new ArrayList<>();
        canAttack = new ArrayList<>();
    }

    /**
     * Uniquely add elements of the 'other' move list to this list. This method only
     * mutates this list, not the other list.
     * 
     * @param other The other list.
     */
    public void add(MoveList other) {
        ListUtils.addAllUnique(canMoveTo, other.canMoveTo);
        ListUtils.addAllUnique(canAttack, other.canAttack);
    }

    /**
     * Clear the move list.
     */
    public void clear() {
        canMoveTo.clear();
        canAttack.clear();
    }
}
