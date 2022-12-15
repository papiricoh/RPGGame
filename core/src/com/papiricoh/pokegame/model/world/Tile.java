package com.papiricoh.pokegame.model.world;

import com.badlogic.gdx.graphics.Texture;
import com.papiricoh.pokegame.model.Actor;

public class Tile {
    private Texture  texture;
    private Actor actor;

    private boolean walkable = true;

    public Tile(String texture_path){
        this.texture = new Texture(texture_path);
    }

    public Tile(String texture_path, boolean walkable){
        this.texture = new Texture(texture_path);
        this.walkable = walkable;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Texture getTexture() {
        return texture;
    }
}
