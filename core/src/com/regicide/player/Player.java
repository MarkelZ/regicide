package com.regicide.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.movement.KingPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public class Player extends Piece {

    public enum Mode {
        // Player is chosing the next action
        SelectMode,
        // Animating action
        AnimatingMode,
        // Idle, waiting for oponent's actions
        IdleMode
    }

    // Mode, what the player is currently doing
    protected Mode mode;

    // Player's inventory
    protected Inventory inventory;

    // Tile selector
    protected PlayerTileSelector tileSelector;

    public Player(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Friendly, pos);

        // Animation
        Texture texture = new Texture("flop.png");
        animation = new SpriteAnimation(texture, worldPos, 16, 16, 8);

        // Move pattern
        this.movePattern = new KingPattern();

        // State of the player
        mode = Mode.SelectMode;

        // Tile selector;
        tileSelector = new PlayerTileSelector(this, gs);
        tileSelector.refreshMoveList();
    }

    @Override
    public void update(float tdelta) {
        // Update animations
        animation.update(tdelta);

        // Update selector
        tileSelector.update(tdelta);

        // Update player
        switch (mode) {
            case AnimatingMode:
                break;
            case IdleMode:
                break;
            case SelectMode:
                // TODO
                // This is for testing
                // Teleports player to clicked position if it is an allowed move
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    Vector2 mousePos = gs.getMousePosInGameWorld();
                    TilePosition pos = board.worldCoordsToBoardIndices(mousePos);
                    MoveList ml = tileSelector.getMoveList();
                    if (TilePosition.listContains(ml.canMoveTo, pos)) {
                        moveTo(pos);
                        board.computeNextPiecePositions();
                        tileSelector.refreshMoveList();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch);

        // Draw select animation if in select mode
        if (mode == Mode.SelectMode) {
            tileSelector.draw(batch);
        }
    }

    public MoveList getMoveList() {
        return movePattern.getMoves(board, boardPos.i, boardPos.j);
    }

    public MoveList getActiveList() {
        // TODO
        return null;
    }

    public MoveList getDashList() {
        // TODO
        return null;
    }

}
