package com.regicide.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.IUpdatableDrawable;

public class Board implements IUpdatableDrawable {
    public ArrayList<Piece> pieceList;
    public Piece[][] grid;

    public RoomGraph rooms;

    public Board() {
        rooms = new RoomGraph();
        rooms.addVertex(new Room(12, 12));
        grid = new Piece[12][12];
        pieceList = new ArrayList<>();

        // needs more work
        Knight knight = new Knight(null);
        knight.x = 4 * 16;
        knight.y = 4 * 16;
        grid[4][4] = knight;
        pieceList.add(knight);

        Knight knight2 = new Knight(null);
        knight2.x = 7 * 16;
        knight2.y = 9 * 16;
        grid[7][2] = knight2;
        pieceList.add(knight2);
    }

    @Override
    public void update(float tdelta) {
        for (Room r : rooms.getVertexList()) {
            r.update(tdelta);
        }

        for (Piece p : pieceList) {
            p.update(tdelta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (Room r : rooms.getVertexList()) {
            r.draw(batch);
        }

        for (Piece p : pieceList) {
            p.draw(batch);
        }
    }
}
