package com.regicide.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.regicide.IUpdatableDrawable;

public class SpriteAnimation implements IUpdatableDrawable {
    Texture animationSprite;

    public SpriteAnimation(Texture animationSprite) {
        this.animationSprite = animationSprite;
    }

    @Override
    public void update(float tdelta) {
    }

    @Override
    public void draw(SpriteBatch batch) {
    }
}
