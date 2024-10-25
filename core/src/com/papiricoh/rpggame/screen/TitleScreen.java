package com.papiricoh.rpggame.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.rpggame.RPGGame;

public class TitleScreen extends AbstractScreen{

    private SpriteBatch batch;
    private int timePassed;
    private GameScreen gameScreen;

    public TitleScreen(RPGGame app, GameScreen gameScreen) {
        super(app);
        this.gameScreen = gameScreen;
        this.batch = new SpriteBatch();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.end();
        timePassed += delta;
        if(timePassed > 1000) {
            getApp().setScreen(gameScreen);
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
