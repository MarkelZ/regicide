package com.regicide.particle;

import com.badlogic.gdx.math.Vector2;
import com.regicide.IUpdatableDrawable;
import com.regicide.scene.GameplayScene;

public abstract class Particle implements IUpdatableDrawable {
    // Game state
    protected GameplayScene gs;

    // Position and velocity
    protected Vector2 position;
    protected Vector2 velocity;

    // Physics coeficients
    protected float friction;
    protected float gravityMul;
    protected final float G = -1;

    // Despawn
    protected float despawnTime;
    protected float timer;
    protected boolean isDespawnable;

    public Particle(GameplayScene gs, Vector2 position) {
        this.gs = gs;

        this.position = position;
        velocity = new Vector2();

        friction = 0.99f;
        gravityMul = 1;

        despawnTime = 0;
        timer = 0;
        isDespawnable = false;
    }

    @Override
    public void update(float tdelta) {
        // Dynamics
        position.add(velocity);
        velocity.scl(friction);
        velocity.y += G * gravityMul;

        // Despawn if timer is over
        if (isDespawnable) {
            timer += tdelta;
            if (timer >= despawnTime) {
                gs.removeParticle(this);
            }
        }
    }
}
