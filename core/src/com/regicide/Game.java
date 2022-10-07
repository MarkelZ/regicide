package com.regicide;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.regicide.board.Board;
import com.regicide.music.MusicInterpolator;
import com.regicide.music.MusicInterpolator.SongChoice;

public class Game extends ApplicationAdapter {
	public static int WIDTH = 360;
	public static int HEIGHT = 240;

	public static final int FPS = 60;
	public static final float SPF = 1.f / FPS;

	// Graphics
	private SpriteBatch batch;
	private Viewport viewport;
	private OrthographicCamera camera;
	private int scale;

	// Objects for debugging
	private MusicInterpolator testMusicPlayer;
	private Board testBoard;

	@Override
	public void create() {
		// Sprites and resources
		batch = new SpriteBatch();

		testBoard = new Board();
		testMusicPlayer = new MusicInterpolator(
				Gdx.files.internal("bossmain.wav"),
				Gdx.files.internal("bosspitchshift.wav"));

		// Graphics
		scale = 3;
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		Gdx.graphics.setWindowedMode(WIDTH * scale, HEIGHT * scale);

		// Start up
		testMusicPlayer.setVolume(0.5f);
		testMusicPlayer.play();
	}

	@Override
	public void render() {
		// Update
		if (Gdx.input.isKeyPressed(Keys.W))
			camera.translate(0, 2, 0);
		if (Gdx.input.isKeyPressed(Keys.S))
			camera.translate(0, -2, 0);
		if (Gdx.input.isKeyPressed(Keys.D))
			camera.translate(2, 0, 0);
		if (Gdx.input.isKeyPressed(Keys.A))
			camera.translate(-2, 0, 0);
		camera.update();

		if (Gdx.input.isKeyJustPressed(Keys.J))
			testMusicPlayer.changeToSong(SongChoice.S1);
		if (Gdx.input.isKeyJustPressed(Keys.K))
			testMusicPlayer.changeToSong(SongChoice.S2);
		testMusicPlayer.update(SPF);

		testBoard.update(FPS);

		// Draw
		ScreenUtils.clear(0, 0, 0, 1);
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		testBoard.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
