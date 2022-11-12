package com.regicide.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.board.Piece;
import com.regicide.gamestate.GameplayGameState;

public class Player extends Piece {
    private Texture texture;

    public Player(GameplayGameState gs) {
        super(gs);

        texture = new Texture("flop.png");
    }

    @Override
    public void update(float tdelta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

}
