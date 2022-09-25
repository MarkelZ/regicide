package com.regicide.board;

import java.util.Random;

import com.regicide.gamestate.GameplayGameState;
import com.regicide.util.Distribution;

public class PieceSpawner extends Piece {
    private Distribution<Piece> distribution;

    public PieceSpawner(GameplayGameState gs, Distribution<Piece> distribution) {
        super(gs);
        this.distribution = distribution;
    }

    public void update(float tdelta) {
    }

    public void draw() {

    }

    public Piece getPiece(Random rng) {
        return distribution.sample(rng);
    }

}