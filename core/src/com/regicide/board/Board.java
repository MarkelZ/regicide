package com.regicide.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.IUpdatableDrawable;

public class Board implements IUpdatableDrawable {
    public ArrayList<Piece> pieceList;
    public Piece[][] pieceGrid;

    public RoomGraph rooms;
    // Room corresponding to each tile in the grid
    public Room[][] roomGrid;

    public final int width = 16;
    public final int height = 12;

    // Size of a tile in world units
    public final int tileSize = 16;

    public Board() {
        rooms = new RoomGraph();
        rooms.addVertex(new Room(width, height));
        roomGrid = new Room[width][height];

        pieceGrid = new Piece[width][height];
        pieceList = new ArrayList<>();

        Knight knight = new Knight(null);
        addPiece(knight, 4, 4);

        Knight knight2 = new Knight(null);
        addPiece(knight2, 7, 9);
    }

    public void addPiece(Piece p, int i, int j) {
        p.i = i;
        p.j = j;
        p.x = i * tileSize;
        p.y = j * tileSize;
        pieceGrid[i][j] = p;
        pieceList.add(p);
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
