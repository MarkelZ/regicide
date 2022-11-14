package com.regicide.gamestate;

import com.regicide.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import box2dLight.RayHandler;

import com.regicide.animation.AnimationManager;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Board;
import com.regicide.camera.GameplayCamManager;
import com.regicide.music.MusicInterpolator;

public class GameplayGameState extends GameState {
    public World world;
    public RayHandler rayHandler;

    protected AnimationManager animationManager;
    protected MusicInterpolator musicPlayer;
    protected Board board;
    protected GameplayCamManager camManager;

    public GameplayGameState(Game game) {
        super(game);

        animationManager = new AnimationManager();
        musicPlayer = new MusicInterpolator(
                Gdx.files.internal("bossmain.wav"),
                Gdx.files.internal("bosspitchshift.wav"));
        musicPlayer.setVolume(0.5f);
        musicPlayer.play();
        camManager = new GameplayCamManager(game.getCamera());

        board = new Board(this);
    }

    @Override
    public void update(float tdelta) {
        animationManager.update(tdelta);
        musicPlayer.update(tdelta);
        camManager.update(tdelta);
        board.update(tdelta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        board.draw(batch);
    }

    public void syncAnimation(SpriteAnimation animation) {

    }
}
