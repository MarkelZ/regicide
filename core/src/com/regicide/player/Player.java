package com.regicide.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.input.InputManager;
import com.regicide.input.InputManager.Action;
import com.regicide.item.ItemManager;
import com.regicide.item.items.AutoDash;
import com.regicide.movement.KingPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.RookPattern;
import com.regicide.movement.TilePosition;
import com.regicide.scene.GameplayScene;
import com.regicide.scene.GameplayScene.State;

public class Player extends Piece {

    // Player's stats
    protected final PlayerStats stats;

    // Player's inventory
    protected final Inventory inventory;

    // Tile selector
    protected final PlayerTileSelector tileSelector;

    // Item manager
    protected final ItemManager itemManager;

    public Player(GameplayScene gs, TilePosition pos) {
        super(gs, Kind.Friendly, pos);

        // Animation
        Texture texture = new Texture("flop.png");
        animation = new SpriteAnimation(texture, worldPos, 16, 16, 8);

        // Move pattern
        this.movePattern = new KingPattern();
        // this.movePattern = new RookPattern();

        // Stats
        stats = new PlayerStats();

        // Inventory
        inventory = new Inventory();

        // Tile selector;
        tileSelector = new PlayerTileSelector(this, gs);
        tileSelector.refreshMoveList();

        // Item manager
        itemManager = new ItemManager(gs.getBoard());
        // TODO: this is for testing items
        itemManager.addItem(new AutoDash(gs));
    }

    @Override
    public void update(float tdelta) {
        // Update items
        itemManager.update(tdelta);

        // Update animations
        animation.update(tdelta);

        // Update selector
        tileSelector.update(tdelta);

        // Debug
        if (InputManager.isActionJustPressed(Action.Move))

        {
            TilePosition target = new TilePosition(boardPos.i + 1, boardPos.j + 1);
            animatePieceAttack(target, 20);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch);

        // Draw select animation if in select mode
        if (gs.getState() == State.PlayerThinking) {
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

    public MoveList getSelectionList() {
        return tileSelector.getMoveList();
    }

    public void refreshSelector() {
        tileSelector.refreshMoveList();
    }

}
