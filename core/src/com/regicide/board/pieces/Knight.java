package com.regicide.board.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.board.Board;
import com.regicide.board.Piece;
import com.regicide.gamestate.GameplayGameState;
import com.regicide.movement.KnightPattern;
import com.regicide.particle.BreakParticle;

public class Knight extends Piece {
    protected static Texture animationSprite; // should be added to spriteanimation

    public Knight(GameplayGameState gs) {
        super(gs, Kind.Hostile, new KnightPattern());

        if (animationSprite == null) {
            animationSprite = new Texture("pieces/knight.png");
        }
    }

    @Override
    public void update(float tdelta) {
        // Debug
        // When space is pressed, explode into tiny pieces (Without despawning myself)
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            Board b = gs.getBoard();
            for (int _ = 0; _ < 20; _++) {
                gs.addParticle(new BreakParticle(gs, new Vector2(x + b.halfTileSize, y + b.halfTileSize),
                        animationSprite, 8, 8));
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animationSprite, x, y);
    }
}