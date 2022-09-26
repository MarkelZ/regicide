package com.regicide.board;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.IUpdatableDrawable;

public class Board implements IUpdatableDrawable {
    public ArrayList<Piece> pieceList;
    public Piece[][] grid;

    private static final int TILE_RENDER_SIZE = 16;

    public Board() {
        grid = new Piece[12][12];
    }

    @Override
    public void update(float tdelta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(SpriteBatch batch) {
        // TODO Auto-generated method stub

    }
}
