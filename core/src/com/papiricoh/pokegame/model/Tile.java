package com.papiricoh.pokegame.model;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private Texture  texture;
    private Actor actor;

    public Tile(String texture_path){
        this.texture = new Texture(texture_path);
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
