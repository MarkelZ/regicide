package com.regicide.board;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.movement.NonMoving;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;
import com.regicide.util.Distribution;

public class PieceSpawner extends Piece {
    private Distribution<Piece> distribution;

    public PieceSpawner(GameplayScene gs, TilePosition pos, Distribution<Piece> distribution) {
        super(gs, Kind.None, new NonMoving(), pos);
        this.distribution = distribution;
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