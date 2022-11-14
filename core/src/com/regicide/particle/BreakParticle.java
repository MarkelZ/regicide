package com.regicide.particle;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.regicide.gamestate.GameplayGameState;

public class BreakParticle extends Particle {
    // Texture from which to render a rectangular segment
    private Texture texture;

    // Position of the segments top-left corner relative to texture
    private int texx;
    private int texy;

    // Size of the segment
    private int width;
    private int height;

    // Random number generator to randomize the size and position of the segment
    private static Random rnd;

    public BreakParticle(GameplayGameState gs, Vector2 position, Texture texture, int maxWidth, int maxHeight) {
        super(gs, position);
        this.texture = texture;

        // Create new rng if not initialized yet
        if (rnd == null) {
            rnd = new Random();
        }

        // Randomize size of segment
        width = rnd.nextInt(maxWidth) + 1;
        height = rnd.nextInt(maxHeight) + 1;

        // Randomize position of segment
        texx = rnd.nextInt(texture.getWidth() - width + 1);
        texy = rnd.nextInt(texture.getHeight() - height + 1);

        // Dynamics
        gravityMul = 0.1f;
        friction = 0.95f;

        // Randomize velocity
        float maxvel = 4;
        velocity.x = (rnd.nextFloat() * 2 - 1) * maxvel;
        velocity.y = rnd.nextFloat() * maxvel;

        // Despawning
        isDespawnable = true;
        despawnTime = .5f;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texx, texy, width, height);
    }
}
