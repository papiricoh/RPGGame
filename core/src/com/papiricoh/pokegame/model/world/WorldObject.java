package com.papiricoh.pokegame.model.world;

import com.badlogic.gdx.graphics.Texture;
import com.papiricoh.pokegame.model.world.events.Interaction;

public abstract class WorldObject {
    private int x;
    private int y;
    private Texture texture;

    private boolean collision = true;

    public WorldObject(int x, int y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }

    public WorldObject(int x, int y, Texture texture, boolean collision) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.collision = collision;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setCoords(int dest_x, int dest_y) {
        this.x = dest_x;
        this.y = dest_y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public abstract Interaction interact();

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
