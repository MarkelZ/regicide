package com.regicide.player;

// Base stats and modifiers (additive and multipliers)
public class PlayerStats {
    public float maxHealth;
    public float health;
    public float attackDamage;

    public PlayerStats() {
        this.maxHealth = 3;
        this.health = this.maxHealth;
        this.attackDamage = 10.f;
    }
}
