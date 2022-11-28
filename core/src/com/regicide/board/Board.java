package com.regicide.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.board.pieces.Bishop;
import com.regicide.board.pieces.Knight;
import com.regicide.board.pieces.Rook;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public class Board implements IUpdatableDrawable {
    public ArrayList<Piece> pieceList;

    // Piece buffers
    public Piece[][] pieceGrid;

    // Graph of rooms
    // It needs to be implemented yet, so it not used
    public RoomGraph rooms;

    // Grid representing which tile belongs to which room
    public Room[][] roomGrid;

    // Debug
    public final int width = 16;
    public final int height = 12;

    // Size of a tile in world units
    public final int tileSize = 16;
    public final int halfTileSize = tileSize / 2;

    // The player
    protected Player player;

    // Game state
    protected GameplayScene gs;

    public Board(GameplayScene gs) {
        this.gs = gs;

        rooms = new RoomGraph();
        roomGrid = new Room[width][height];

        pieceGrid = new Piece[width][height];

        pieceList = new ArrayList<>();
    }

    // Debug
    public void generateTestWorld() {
        rooms.addVertex(new Room(width, height));

        Knight knight = new Knight(gs, new TilePosition(3, 3));
        addPiece(knight);

        Bishop bishop = new Bishop(gs, new TilePosition(2, 10));
        addPiece(bishop);

        Rook rook = new Rook(gs, new TilePosition(6, 5));
        addPiece(rook);

        player = new Player(gs, new TilePosition(10, 10));
        addPiece(player);
    }

    public void addPiece(Piece p) {
        pieceGrid[p.boardPos.i][p.boardPos.j] = p;
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

    public boolean isWithinBounds(int i, int j) {
        return i >= 0 && i < width && j >= 0 && j < height;
    }

    public TilePosition worldCoordsToBoardIndices(Vector2 pos) {
        TilePosition result = new TilePosition((int) (pos.x / tileSize), (int) (pos.y / tileSize));
        if (isWithinBounds(result.i, result.j))
            return result;
        else
            return null;
    }

    public Vector2 boardIndicesToWorldCoords(TilePosition pos) {
        return new Vector2(pos.i * tileSize, pos.j * tileSize);
    }
}
