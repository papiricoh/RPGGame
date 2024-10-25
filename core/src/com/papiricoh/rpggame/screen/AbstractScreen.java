package com.papiricoh.rpggame.screen;

import com.badlogic.gdx.Screen;
import com.papiricoh.rpggame.RPGGame;

public abstract class AbstractScreen implements Screen {

    private RPGGame app;

    public AbstractScreen(RPGGame app) {
        this.app = app;
    }

    @Override
    public abstract void dispose();

    @Override
    public abstract void show();

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public abstract void hide();

    public RPGGame getApp() {
        return app;
    }

}
