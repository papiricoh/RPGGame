package com.papiricoh.rpggame.model.world.objects;

import com.badlogic.gdx.graphics.Texture;

import java.util.UUID;

public class WorldObject {
    private UUID uuid;
    private String name;
    private Texture  texture;

    public WorldObject(String name, Texture texture) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Texture getTexture() {
        return this.texture;
    }
}
