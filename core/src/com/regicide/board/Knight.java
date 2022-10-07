package com.regicide.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.gamestate.GameplayGameState;

public class Knight extends Piece {
    private static Texture animationSprite; // should be added to spriteanimation
    public int x;
    public int y;

    public Knight(GameplayGameState gs) {
        super(gs);

        if (animationSprite == null) {
            animationSprite = new Texture("pieces/knight.png");
        }
    }

    @Override
    public void update(float tdelta) {
        //
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animationSprite, x, y);
    }
}
