package com.regicide.item;

import java.util.ArrayList;
import java.util.List;

import com.regicide.board.Board;

public class ItemManager {
    protected final List<Item> items;
    protected final Board board;

    public ItemManager(Board board) {
        this.board = board;
        items = new ArrayList<>();
    }

    // Update call every tick
    public void update(float tdelta) {
        // Update all items
        for (Item item : items) {
            item.update(tdelta);
        }
    }

    public void addItem(Item item) {
        board.addEventObserver(item);
        items.add(item);
    }

    public void removeItem(Item item) {
        // TODO: remove the item without breaking anything!
    }
}
