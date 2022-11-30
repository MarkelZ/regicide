package com.regicide.board.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.movement.BishopPattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public class Bishop extends HostilePiece {
    protected static Texture texture; // should be added to spriteanimation

    public Bishop(GameplayScene gs, TilePosition pos) {
        super(gs, pos);

        if (texture == null) {
            texture = new Texture("pieces/bishop.png");
        }

        initialize(new BishopPattern(), texture);
    }

    @Override
    public void update(float tdelta) {
        super.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
