package com.regicide.board;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.gamestate.GameplayGameState;
import com.regicide.util.Distribution;

public class PieceSpawner extends Piece {
    private Distribution<Piece> distribution;

    public PieceSpawner(GameplayGameState gs, Distribution<Piece> distribution) {
        super(gs);
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