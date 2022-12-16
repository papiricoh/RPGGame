package com.papiricoh.pokegame.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.papiricoh.pokegame.screen.ui.OptionBox;

public class OptionBoxController extends InputAdapter {
    private OptionBox box;

    public OptionBoxController(OptionBox optionBox) {
        this.box = optionBox;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.I) {
            box.moveUp();
            return true;
        } else if (keycode == Keys.K) {
            box.moveDown();
            return true;
        }
        return false;
    }
}
