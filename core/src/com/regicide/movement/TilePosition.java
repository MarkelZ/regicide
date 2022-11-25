package com.regicide.movement;

import java.util.List;

public class TilePosition implements Comparable<Object> {
    public int i;
    public int j;

    public TilePosition(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof TilePosition) {
            TilePosition t = (TilePosition) o;
            return t.i == i ? t.j - j : t.i - i;
        } else {
            return -1;
        }
    }

    // TODO: meh
    public static boolean listContains(List<TilePosition> list, TilePosition position) {
        for (TilePosition p : list)
            if (p.i == position.i && p.j == position.j)
                return true;
        return false;
    }
}
