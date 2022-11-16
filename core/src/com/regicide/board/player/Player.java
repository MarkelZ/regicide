package com.regicide.board.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Piece;
import com.regicide.gamestate.GameplayGameState;
import com.regicide.movement.KnightPattern;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;

public class Player extends Piece {
    private SpriteAnimation animation;
    private SpriteAnimation selectedTileAnimation;

    public Player(GameplayGameState gs) {
        super(gs, Kind.Friendly, new KnightPattern());

        Texture texture = new Texture("flop.png");
        animation = new SpriteAnimation(texture, 16, 16, 8);
        Texture selectTexture = new Texture("selectred.png");
        selectedTileAnimation = new SpriteAnimation(selectTexture, 16, 16, 8);
    }

    @Override
    public void update(float tdelta) {
        animation.update(tdelta);
        selectedTileAnimation.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        animation.draw(batch, x, y);

        // TODO: dont do * 16
        MoveList ml = movePattern.getMoves(gs.getBoard(), i, j);
        for (TilePosition pos : ml.canMoveTo) {
            selectedTileAnimation.draw(batch, pos.i * 16, pos.j * 16);
        }
    }

}
