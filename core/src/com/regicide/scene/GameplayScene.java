package com.regicide.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.regicide.Game;
import com.regicide.animation.AnimationManager;
import com.regicide.animation.SpriteAnimation;
import com.regicide.board.Board;
import com.regicide.camera.GameplayCamManager;
import com.regicide.fight.ActionType;
import com.regicide.movement.MoveList;
import com.regicide.movement.TilePosition;
import com.regicide.music.MusicInterpolator;
import com.regicide.particle.Particle;
import com.regicide.player.Player;

import box2dLight.RayHandler;

public class GameplayScene extends Scene {
    public enum State {
        PlayerThinking,
        PlayerTransitioning,
        EnemyTransitioning
    }

    protected State state;

    protected World world;
    protected RayHandler rayHandler;

    protected AnimationManager animationManager;
    protected MusicInterpolator musicPlayer;
    protected GameplayCamManager camManager;

    protected Board board;

    protected ArrayList<Particle> particles;
    protected ArrayList<Particle> particlesToAdd;
    protected ArrayList<Particle> particlesToRmv;

    public GameplayScene(Game game) {
        super(game);

        // Resources
        animationManager = new AnimationManager();
        musicPlayer = new MusicInterpolator(
                Gdx.files.internal("bossmain.wav"),
                Gdx.files.internal("bosspitchshift.wav"));
        musicPlayer.setVolume(0.5f);
        musicPlayer.play();
        camManager = new GameplayCamManager(game.getCamera());

        // Generate board
        board = new Board(this);
        board.generateTestWorld();

        // Particles
        particles = new ArrayList<>();
        particlesToAdd = new ArrayList<>();
        particlesToRmv = new ArrayList<>();

        // Game state
        state = State.PlayerThinking;
    }

    @Override
    public void update(float tdelta) {
        // Update utils
        animationManager.update(tdelta);
        musicPlayer.update(tdelta);
        camManager.update(tdelta);

        // Update particles
        for (Particle p : particles) {
            p.update(tdelta);
        }

        // Add and remove particles
        particles.addAll(particlesToAdd);
        particles.removeAll(particlesToRmv);
        particlesToAdd.clear();
        particlesToRmv.clear();

        // Update board
        board.update(tdelta);

        // State
        switch (state) {
            case PlayerThinking:
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    Vector2 mousePos = getMousePosInGameWorld();
                    TilePosition pos = board.worldCoordsToBoardIndices(mousePos);
                    Player player = board.getPlayer();
                    MoveList ml = player.getSelectionList();
                    if (TilePosition.listContains(ml.canMoveTo, pos)) {
                        TilePosition origin = player.boardPos;
                        player.animateAndMoveTo(pos, 20);
                        state = State.PlayerTransitioning;
                        // TODO: This should happen after player is done animating
                        // Should add new state that comes right after this one, for items doing stuff
                        board.getEventManager().notifyPlayerAction(origin, pos, ActionType.Move);
                    }
                }
                break;
            case PlayerTransitioning:
                if (!board.getPlayer().isAnimating()) {
                    state = State.EnemyTransitioning;
                    board.computeNextPiecePositions();
                    board.getPlayer().refreshSelector();
                    state = State.EnemyTransitioning;
                }
                break;
            case EnemyTransitioning:
                if (!board.isAnyPieceAnimating()) {
                    state = State.PlayerThinking;
                }
                break;
            default:
                System.out.println("WARNING: Gameplay scene in unknown state.");
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        // Draw board
        board.draw(batch);

        // Draw particles
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

    public Vector2 getMousePosInGameWorld() {
        return game.getMousePosInGameWorld();
    }

    // Getters and setters below

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public State getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public MusicInterpolator getMusicPlayer() {
        return musicPlayer;
    }

    public GameplayCamManager getCamManager() {
        return camManager;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public ArrayList<Particle> getParticlesToAdd() {
        return particlesToAdd;
    }

    public ArrayList<Particle> getParticlesToRmv() {
        return particlesToRmv;
    }

    public OrthographicCamera getCamera() {
        return game.getCamera();
    }

}
