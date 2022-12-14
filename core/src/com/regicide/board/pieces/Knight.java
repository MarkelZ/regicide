package com.regicide.board.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.movement.KnightPattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public class Knight extends HostilePiece {
    protected static Texture texture; // should be added to spriteanimation

    public Knight(GameplayScene gs, TilePosition pos) {
        super(gs, pos);

        if (texture == null) {
            texture = new Texture("pieces/knight.png");
        }

        initialize(new KnightPattern(), texture);
    }

    @Override
    public void update(float tdelta) {
        super.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void calculateNextPos() {
        nextPosClosestToPlayer();
    }
}
