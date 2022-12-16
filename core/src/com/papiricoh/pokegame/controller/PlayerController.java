package com.papiricoh.pokegame.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.papiricoh.pokegame.model.Actor;
import sun.tools.jconsole.JConsole;

public class PlayerController extends InputAdapter {
    private Actor actor;
    private boolean up, down, left, right;
    private boolean[] buttonPress;
    private float[] buttonTimer;

    private float WALK_REFACE_THRESHOLD = 0.07f;

    public PlayerController(Actor p){
        this.actor = p;
        buttonPress = new boolean[DIRECTION.values().length];
        buttonPress[DIRECTION.NORTH.ordinal()] = false;
        buttonPress[DIRECTION.SOUTH.ordinal()] = false;
        buttonPress[DIRECTION.EAST.ordinal()] = false;
        buttonPress[DIRECTION.WEST.ordinal()] = false;
        buttonTimer = new float[DIRECTION.values().length];
        buttonTimer[DIRECTION.NORTH.ordinal()] = 0.0f;
        buttonTimer[DIRECTION.SOUTH.ordinal()] = 0.0f;
        buttonTimer[DIRECTION.EAST.ordinal()] = 0.0f;
        buttonTimer[DIRECTION.WEST.ordinal()] = 0.0f;
    }

    public boolean keyDown(int keyCode) {
        if(keyCode == Keys.UP) {
            buttonPress[DIRECTION.NORTH.ordinal()] = true;
        }
        if(keyCode == Keys.DOWN) {
            buttonPress[DIRECTION.SOUTH.ordinal()] = true;
        }
        if(keyCode == Keys.RIGHT) {
            buttonPress[DIRECTION.EAST.ordinal()] = true;
        }
        if(keyCode == Keys.LEFT) {
            buttonPress[DIRECTION.WEST.ordinal()] = true;
        }
        return false;
    }
    public boolean keyUp(int keyCode) {
        if(keyCode == Keys.UP) {
            releaseDirection(DIRECTION.NORTH);
        }
        if(keyCode == Keys.DOWN) {
            releaseDirection(DIRECTION.SOUTH);
        }
        if(keyCode == Keys.RIGHT) {
            releaseDirection(DIRECTION.EAST);
        }
        if(keyCode == Keys.LEFT) {
            releaseDirection(DIRECTION.WEST);
        }
        return false;
    }

    public void update(float delta) {
        if (buttonPress[DIRECTION.NORTH.ordinal()]) {
            updateDirection(DIRECTION.NORTH, delta);
            return;
        }
        if (buttonPress[DIRECTION.SOUTH.ordinal()]) {
            updateDirection(DIRECTION.SOUTH, delta);
            return;
        }
        if (buttonPress[DIRECTION.WEST.ordinal()]) {
            updateDirection(DIRECTION.WEST, delta);
            return;
        }
        if (buttonPress[DIRECTION.EAST.ordinal()]) {
            updateDirection(DIRECTION.EAST, delta);
            return;
        }
    }

    private void updateDirection(DIRECTION dir, float delta) {
        buttonTimer[dir.ordinal()] += delta;
        considerMove(dir);
    }

    private void releaseDirection(DIRECTION dir) {
        buttonPress[dir.ordinal()] = false;
        considerReface(dir);
        buttonTimer[dir.ordinal()] = 0f;
    }

    private void considerMove(DIRECTION dir) {
        if (buttonTimer[dir.ordinal()] > WALK_REFACE_THRESHOLD) {
            actor.move(dir);
        }
    }

    private void considerReface(DIRECTION dir) {
        if(buttonTimer[dir.ordinal()] < WALK_REFACE_THRESHOLD) {
            actor.reface(dir);
        }
    }
}
