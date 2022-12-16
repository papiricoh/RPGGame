package com.papiricoh.pokegame.model.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.papiricoh.pokegame.model.world.WorldObject;
import com.papiricoh.pokegame.model.world.events.Interaction;

public class PokeballWorldObject extends WorldObject {

    public PokeballWorldObject(int x, int y) {
        super(x, y, new Texture("objects/pokeball.png"));

    }

    @Override
    public Interaction interact() {
        return null;
    }
}
