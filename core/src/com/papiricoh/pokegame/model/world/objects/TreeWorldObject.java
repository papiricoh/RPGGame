package com.papiricoh.pokegame.model.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.papiricoh.pokegame.model.world.WorldObject;
import com.papiricoh.pokegame.model.world.events.Interaction;

public class TreeWorldObject extends WorldObject {

    public TreeWorldObject(int x, int y) {
        super(x, y, new Texture("objects/xmasTree.png"), false);

    }

    @Override
    public Interaction interact() {
        return null;
    }
}
