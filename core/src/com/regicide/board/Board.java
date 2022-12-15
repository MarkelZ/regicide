package com.regicide.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.board.pieces.Bishop;
import com.regicide.board.pieces.Elephant;
import com.regicide.board.pieces.Knight;
import com.regicide.movement.TilePosition;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public class Board implements IUpdatableDrawable {
    // List of pieces
    public List<Piece> pieceList;

    // List of pieces to add and to remove
    protected List<Piece> piecesToAdd;
    protected List<Piece> piecesToRmv;

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

    // List of pieces that are performing an animation
    protected List<Piece> animatingPieces;

    public Board(GameplayScene gs) {
        this.gs = gs;

        rooms = new RoomGraph();
        roomGrid = new Room[width][height];

        pieceGrid = new Piece[width][height];

        pieceList = new ArrayList<>();
        piecesToAdd = new ArrayList<>();
        piecesToRmv = new ArrayList<>();
    }

    // Debug
    public void generateTestWorld() {
        rooms.addVertex(new Room(width, height));

        Knight knight = new Knight(gs, new TilePosition(3, 3));
        addPiece(knight);

        Bishop bishop = new Bishop(gs, new TilePosition(2, 10));
        addPiece(bishop);

        // Rook rook = new Rook(gs, new TilePosition(6, 5));
        // addPiece(rook);

        Elephant elephant = new Elephant(gs, new TilePosition(6, 7));
        addPiece(elephant);

        player = new Player(gs, new TilePosition(10, 10));
        addPiece(player);

        animatingPieces = new ArrayList<>();
    }

    @Override
    public void update(float tdelta) {
        // Add pieces
        for (Piece p : piecesToAdd) {
            pieceGrid[p.boardPos.i][p.boardPos.j] = p;
            pieceList.add(p);
        }
        piecesToAdd.clear();

        // Remove pieces
        for (Piece p : piecesToRmv) {
            pieceGrid[p.boardPos.i][p.boardPos.j] = null;
            pieceList.remove(p);
        }
        piecesToRmv.clear();

        // Update rooms
        for (Room r : rooms.getVertexList()) {
            r.update(tdelta);
        }

        // Update pieces
        for (Piece p : pieceList) {
            p.update(tdelta);
        }

        // Piece animations
        List<Piece> animRemove = new ArrayList<>();
        for (Piece p : animatingPieces) {
            if (!p.isAnimating()) {
                animRemove.add(p);
            }
        }
        animatingPieces.removeAll(animRemove);
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

    // Add piece to the board
    public void addPiece(Piece p) {
        piecesToAdd.add(p);
    }

    // Remove piece from the board
    public void removePiece(Piece p) {
        piecesToRmv.add(p);
    }

    // Get the piece at tile position pos
    public Piece getPiece(TilePosition pos) {
        return pieceGrid[pos.i][pos.j];
    }

    // Whether (i, j) is within the bounds of the board
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

    // Move every piece for one turn
    public void computeNextPiecePositions() {
        // Randomly iterate through the pieces
        ArrayList<Piece> piecesRand = new ArrayList<>(pieceList);
        Collections.shuffle(piecesRand);

        for (Piece p : piecesRand) {
            p.calculateNextPos();
        }

        // Move pieces to next positions
        for (Piece p : pieceList) {
            p.moveToNextPos();
        }

        // Animation
        animatingPieces = new ArrayList<>(pieceList);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAnyPieceAnimating() {
        if (animatingPieces == null) {
            return false;
        }

        for (Piece piece : animatingPieces) {
            if (piece.isAnimating()) {
                return true;
            }
        }

        return false;
    }
}
