package com.regicide.board.pieces;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.board.Piece;
import com.regicide.movement.NonMoving;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;
import com.regicide.util.Distribution;

public class PieceSpawner extends Piece {
    private Distribution<Piece> distribution;

    public PieceSpawner(GameplayScene gs, TilePosition pos, Distribution<Piece> distribution) {
        super(gs, Kind.None, pos);
        this.distribution = distribution;
        this.movePattern = new NonMoving();
        this.animation = null;
    }

    @Override
    public void update(float tdelta) {
    }

    @Override
    public void draw(SpriteBatch batch) {
    }

    public Piece getPiece(Random rng) {
        return distribution.sample(rng);
    }

}