package com.regicide.board.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.movement.RookPattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public class Rook extends HostilePiece {
    protected static Texture texture; // should be added to spriteanimation

    public Rook(GameplayScene gs, TilePosition pos) {
        super(gs, pos);

        if (texture == null) {
            texture = new Texture("pieces/rook.png");
        }

        initialize(new RookPattern(), texture);
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
