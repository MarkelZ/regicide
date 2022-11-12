package com.regicide.board;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.regicide.util.LGraph;

public class RoomGraph extends LGraph<Room> {

    public RoomGraph() {

    }

    public RoomGraph(List<Room> rooms) {
        super(rooms);
    }

    public Vector2 calculateDimensions() {
        return new Vector2(0, 0);
    }
}
