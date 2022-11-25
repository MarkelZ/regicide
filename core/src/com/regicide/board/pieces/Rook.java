package com.regicide.board.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.board.Board;
import com.regicide.board.Piece;
import com.regicide.movement.BishopPattern;
import com.regicide.movement.TilePosition;
import com.regicide.particle.BreakParticle;
import com.regicide.scene.GameplayScene;

public class Rook extends Piece {
    protected static Texture animationSprite; // should be added to spriteanimation

    public Rook(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Hostile, new BishopPattern(), pos);

        if (animationSprite == null) {
            animationSprite = new Texture("pieces/rook.png");
        }
    }

    @Override
    public void update(float tdelta) {
        // Debug
        // When space is pressed, explode into tiny pieces (Without despawning myself)
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            Board b = gs.getBoard();
            for (int _ = 0; _ < 20; _++) {
                gs.addParticle(
                        new BreakParticle(gs, new Vector2(worldPos.x + b.halfTileSize, worldPos.y + b.halfTileSize),
                                animationSprite, 8, 8));
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animationSprite, worldPos.x, worldPos.y);
    }
}
