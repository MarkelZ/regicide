package com.regicide.board.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.input.InputManager;
import com.regicide.input.InputManager.Action;
import com.regicide.movement.MovePattern;
import com.regicide.movement.TilePosition;
import com.regicide.particle.BreakParticle;
import com.regicide.scene.GameplayScene;

public abstract class HostilePiece extends Piece {
    public HostilePiece(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Hostile, pos);
    }

    public void initialize(MovePattern movePattern, Texture texture) {
        this.movePattern = movePattern;
        animation = new SpriteAnimation(texture, worldPos, board.tileSize, board.tileSize, 8);
    }

    @Override
    public void update(float tdelta) {
        // Update animation
        animation.update(tdelta);

        // Debug
        // Spawn some break particles
        if (InputManager.isActionJustPressed(Action.Dash)) {
            for (int _ = 0; _ < 20; _++) {
                gs.addParticle(
                        new BreakParticle(gs,
                                new Vector2(worldPos.x + board.halfTileSize, worldPos.y + board.halfTileSize),
                                animation.getTexture(), 8, 8));
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch);
    }

    // TODO: All hostile pieces should be of this type.
    // There should be code for finding the closest movable tile to player and stuff
    // like that
}
