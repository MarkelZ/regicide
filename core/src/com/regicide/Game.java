package com.regicide;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.regicide.scene.Scene;
import com.regicide.input.Input;
import com.regicide.scene.GameplayScene;

public class Game extends ApplicationAdapter {
	public static int WIDTH = 300;
	public static int HEIGHT = 200;

	public static final int FPS = 60;
	public static final float SPF = 1.f / FPS;

	// Graphics
	private SpriteBatch batch;
	private Viewport viewport;
	private OrthographicCamera camera;
	private int scale;

	// Game state
	Scene gameState;

	@Override
	public void create() {
		// Sprites and resources
		batch = new SpriteBatch();

		// Graphics
		scale = 4;
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(100, 100, 0);
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		Gdx.graphics.setWindowedMode(WIDTH * scale, HEIGHT * scale);

		// Input
		Input.initialize();

		// Game state
		gameState = new GameplayScene(this);
	}

	@Override
	public void render() {
		// Update
		gameState.update(SPF);

		// Draw
		// TODO: Future me, do this instead
		// https://stackoverflow.com/questions/7551669/libgdx-spritebatch-render-to-texture
		ScreenUtils.clear(0, 0, 0, 1);
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		gameState.draw(batch);
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

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Vector2 getMousePosInGameWorld() {
		Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Vector2(pos.x, pos.y);
	}

}
