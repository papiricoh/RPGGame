package com.papiricoh.pokegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.pokegame.PokeGame;
import com.papiricoh.pokegame.Settings;
import com.papiricoh.pokegame.controller.PlayerController;
import com.papiricoh.pokegame.model.Actor;
import com.papiricoh.pokegame.model.TileMap;

public class GameScreen extends AbstractScreen {

    private TileMap tileMap;
    private Actor actor;
    private PlayerController controller;

    private SpriteBatch batch;
    private Texture characterStanding;

    public GameScreen(PokeGame app) {
        super(app);

        characterStanding = new Texture("player/playerTexture.png");
        batch = new SpriteBatch();
        tileMap = new TileMap(20, 20);
        actor = new Actor(tileMap, 1, 1);
        controller = new PlayerController(actor);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        batch.begin();

        int mapWidth = tileMap.getWidth();
        int mapHeight = tileMap.getHeight();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                batch.draw(tileMap.getTile(x, y).getTexture(), x * Settings.SCALED_TILE_SIZE, y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
            }
        }

        batch.draw(characterStanding, actor.getX() * Settings.SCALED_TILE_SIZE, actor.getY() * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
        batch.end();
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
