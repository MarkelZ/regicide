package com.regicide.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.regicide.IUpdatableDrawable;
import com.regicide.player.Player;
import com.regicide.scene.GameplayScene;

public class HUD implements IUpdatableDrawable {
    protected final GameplayScene gs;
    protected final Player player;
    protected final BitmapFont font;

    public HUD(GameplayScene gs) {
        this.gs = gs;
        this.player = gs.getBoard().getPlayer();

        // Generate bitmap font from a true-type font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/freemono.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 16;
        parameter.color = Color.BLACK;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 1.0f;
        this.font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void update(float tdelta) {
        // Update animations
    }

    @Override
    public void draw(SpriteBatch batch) {
        OrthographicCamera cam = gs.getCamera();
        float ox = cam.position.x;
        float oy = cam.position.y;
        float w = cam.viewportWidth;
        float h = cam.viewportHeight;
        float margin = 10.f;
        String text = "HP: " + player.getStats().health;
        font.draw(batch, text, ox - w / 2 + margin, oy + h / 2 - margin);
    }

}
