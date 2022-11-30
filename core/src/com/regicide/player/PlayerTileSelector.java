package com.regicide.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.animation.SpriteAnimation;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;

public class PlayerTileSelector implements IUpdatableDrawable {

    public enum SelectionType {
        // Player is selecting where to move
        MoveSelect,
        // Player is choosing how to use the active item
        ActiveSelect,
        // Player is selecting where to dash
        DashSelect
    }

    // Selection of tiles
    private SelectionType select;
    private MoveList ml;

    // Animations
    private SpriteAnimation moveTileAnimation;
    private SpriteAnimation activeTileAnimation;
    private SpriteAnimation dashTileAnimation;

    // Player
    private Player player;

    // Game state
    private GameplayScene gs;

    public PlayerTileSelector(Player player, GameplayScene gs) {
        this.player = player;
        this.gs = gs;

        // Initialize the animations
        moveTileAnimation = new SpriteAnimation(new Texture("selectred.png"), new Vector2(), 16, 16, 8);
        activeTileAnimation = new SpriteAnimation(new Texture("selectpurple.png"), new Vector2(), 16, 16, 8);
        dashTileAnimation = new SpriteAnimation(new Texture("selectgreen.png"), new Vector2(), 16, 16, 8);

        // Set selection type to move selection
        select = SelectionType.MoveSelect;

        // Start with an empty move list
        ml = new MoveList();
    }

    // Refresh move list based on the selection mode
    public void refreshMoveList() {
        switch (select) {
            case ActiveSelect:
                ml = player.getActiveList();
                break;
            case DashSelect:
                ml = player.getDashList();
                break;
            case MoveSelect:
                ml = player.getMoveList();
                break;
        }
    }

    @Override
    public void update(float tdelta) {
        // Update select animations
        moveTileAnimation.update(tdelta);
        activeTileAnimation.update(tdelta);
        dashTileAnimation.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // Choose the right selection animation
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

        // Draw the animation on each selected tile
        for (TilePosition pos : ml.canMoveTo) {
            Vector2 posWorld = gs.getBoard().boardIndicesToWorldCoords(pos);
            tileAnimation.setPosition(posWorld);
            tileAnimation.draw(batch);
        }
    }

    public MoveList getMoveList() {
        return ml;
    }

}
