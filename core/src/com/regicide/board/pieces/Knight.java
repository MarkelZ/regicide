package com.regicide.board.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.movement.KnightPattern;
import com.regicide.movement.TilePosition;
import com.regicide.particle.BreakParticle;
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
        // Debug
        // When space is pressed, explode into tiny pieces (Without despawning myself)
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            for (int _ = 0; _ < 20; _++) {
                gs.addParticle(
                        new BreakParticle(gs,
                                new Vector2(worldPos.x + board.halfTileSize, worldPos.y + board.halfTileSize),
                                texture, 8, 8));
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, worldPos.x, worldPos.y);
    }
}
