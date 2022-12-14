package com.papiricoh.pokegame.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.papiricoh.pokegame.model.Actor;

public class PlayerController extends InputAdapter {
    private Actor actor;

    public PlayerController(Actor p){
        this.actor = p;
    }

    public boolean keyDown(int keyCode) {
        if(keyCode == Keys.UP) {
            actor.move(0,1);
            return true;
        }
        if(keyCode == Keys.DOWN) {
            actor.move(0,-1);
            return true;
        }
        if(keyCode == Keys.RIGHT) {
            actor.move(1,0);
            return true;
        }
        if(keyCode == Keys.LEFT) {
            actor.move(-1,0);
            return true;
        }
        return false;
    }
}
