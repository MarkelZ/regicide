package com.regicide.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.gamestate.GameplayGameState;

public class Player extends Piece {
    private SpriteAnimation animation;

    public Player(GameplayGameState gs) {
        super(gs);

        Texture texture = new Texture("squishy.png");
        animation = new SpriteAnimation(texture, 16, 16, 8);
    }

    @Override
    public void update(float tdelta) {
        animation.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch, x, y);
    }

}
