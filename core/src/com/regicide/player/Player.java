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

    public enum SelectionType {
        // Player is selecting where to move
        MoveSelect,
        // Player is choosing how to use the active item
        ActiveSelect,
        // Player is selecting where to dash
        DashSelect
    }

    // Mode, what the player is currently doing
    protected Mode mode;

    // Selection of tiles
    protected SelectionType select;
    protected MoveList ml;

    // Animation of the player's sprite
    private SpriteAnimation animation;

    // Animation of the tile selection outline
    private SpriteAnimation moveTileAnimation;
    private SpriteAnimation activeTileAnimation;
    private SpriteAnimation dashTileAnimation;

    // Player's inventory
    protected Inventory inventory;

    public Player(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Friendly, new KingPattern(), pos);

        // Animations
        Texture texture = new Texture("flop.png");
        animation = new SpriteAnimation(texture, 16, 16, 8);
        moveTileAnimation = new SpriteAnimation(new Texture("selectred.png"), 16, 16, 8);
        activeTileAnimation = new SpriteAnimation(new Texture("selectpurple.png"), 16, 16, 8);
        dashTileAnimation = new SpriteAnimation(new Texture("selectgreen.png"), 16, 16, 8);

        // Moves
        mode = Mode.SelectMode;
        select = SelectionType.MoveSelect;
    }

    @Override
    public void update(float tdelta) {
        // Update animations
        animation.update(tdelta);
        moveTileAnimation.update(tdelta);
        activeTileAnimation.update(tdelta);
        dashTileAnimation.update(tdelta);

        // Update player
        switch (mode) {
            case AnimatingMode:
                break;
            case IdleMode:
                break;
            case SelectMode:
                // TODO
                // This is for testing
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    Vector2 mousePos = gs.getMousePosInGameWorld();
                    TilePosition pos = gs.getBoard().worldCoordsToBoardIndices(mousePos);
                    if (ml != null && TilePosition.listContains(ml.canMoveTo, pos)) {
                        gs.getBoard().movePiece(this, pos);
                        ml = movePattern.getMoves(gs.getBoard(), boardPos.i, boardPos.j);
                    }
                }
                break;
            default:
                break;
        }

        if (ml == null) {
            switch (select) {
                case ActiveSelect:
                    break;
                case DashSelect:
                    break;
                case MoveSelect:
                    ml = movePattern.getMoves(gs.getBoard(), boardPos.i, boardPos.j);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch, worldPos.x, worldPos.y);

        // Draw select animation if in select mode
        if (mode == Mode.SelectMode && ml != null) {
            SpriteAnimation tileAnimation = null;
            switch (select) {
                case ActiveSelect:
                    tileAnimation = activeTileAnimation;
                    break;
                case DashSelect:
                    tileAnimation = dashTileAnimation;
                    break;
                case MoveSelect:
                    tileAnimation = moveTileAnimation;
                    break;
            }
            for (TilePosition pos : ml.canMoveTo) {
                Vector2 posWorld = gs.getBoard().boardIndicesToWorldCoords(pos);
                tileAnimation.draw(batch, posWorld.x, posWorld.y);
            }
        }
    }

}
