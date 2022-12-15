package com.papiricoh.pokegame.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.papiricoh.pokegame.model.Actor;

public class PlayerController extends InputAdapter {
    private Actor actor;
    private boolean up, down, left, right;

    public PlayerController(Actor p){
        this.actor = p;
    }

    public boolean keyDown(int keyCode) {
        if(keyCode == Keys.UP) {
            up = true;
        }
        if(keyCode == Keys.DOWN) {
            down = true;
        }
        if(keyCode == Keys.RIGHT) {
            right = true;
        }
        if(keyCode == Keys.LEFT) {
            left = true;
        }
        return false;
    }
    public boolean keyUp(int keyCode) {
        if(keyCode == Keys.UP) {
            up = false;
        }
        if(keyCode == Keys.DOWN) {
            down = false;
        }
        if(keyCode == Keys.RIGHT) {
            right = false;
        }
        if(keyCode == Keys.LEFT) {
            left = false;
        }
        return false;
    }

    public void update(float delta) {
        if (up) {
            actor.move(DIRECTION.NORTH);
            return;
        }
        if (down) {
            actor.move(DIRECTION.SOUTH);
            return;
        }
        if (left) {
            actor.move(DIRECTION.WEST);
            return;
        }
        if (right) {
            actor.move(DIRECTION.EAST);
            return;
        }
    }
}
