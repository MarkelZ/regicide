package com.regicide.gamestate;

import com.regicide.Game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import box2dLight.RayHandler;

import com.regicide.animation.AnimationManager;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Board;
import com.regicide.camera.GameplayCamManager;
import com.regicide.music.MusicInterpolator;
import com.regicide.particle.Particle;

public class GameplayGameState extends GameState {
    public World world;
    public RayHandler rayHandler;

    protected AnimationManager animationManager;
    protected MusicInterpolator musicPlayer;
    protected GameplayCamManager camManager;

    protected Board board;

    protected ArrayList<Particle> particles;
    protected ArrayList<Particle> particlesToAdd;
    protected ArrayList<Particle> particlesToRmv;

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
        particles = new ArrayList<>();
        particlesToAdd = new ArrayList<>();
        particlesToRmv = new ArrayList<>();
    }

    @Override
    public void update(float tdelta) {
        // Update utils
        animationManager.update(tdelta);
        musicPlayer.update(tdelta);
        camManager.update(tdelta);
        board.update(tdelta);

        // Update particles
        for (Particle p : particles) {
            p.update(tdelta);
        }

        // Add and remove particles
        particles.addAll(particlesToAdd);
        particles.removeAll(particlesToRmv);
        particlesToAdd.clear();
        particlesToRmv.clear();
    }

    @Override
    public void draw(SpriteBatch batch) {
        board.draw(batch);

        for (Particle p : particles) {
            p.draw(batch);
        }
    }

    public void addParticle(Particle particle) {
        particlesToAdd.add(particle);
    }

    public void removeParticle(Particle particle) {
        particlesToRmv.add(particle);
    }

    public void syncAnimation(SpriteAnimation animation) {

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
