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

    @Override
    public String toString() {
        return "tp[" + i + ", " + j + "]";
    }

    // Distance squared to tile position
    public int distance2(TilePosition pos) {
        int diffi = i - pos.i;
        int diffj = j - pos.j;
        return diffi * diffi + diffj * diffj;
    }

    // TODO: meh
    public static boolean listContains(List<TilePosition> list, TilePosition position) {
        for (TilePosition p : list)
            if (p.i == position.i && p.j == position.j)
                return true;
        return false;
    }
}
