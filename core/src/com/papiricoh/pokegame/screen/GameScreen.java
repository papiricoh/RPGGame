package com.papiricoh.pokegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.pokegame.PokeGame;
import com.papiricoh.pokegame.Settings;
import com.papiricoh.pokegame.controller.PlayerController;
import com.papiricoh.pokegame.model.Actor;
import com.papiricoh.pokegame.model.Camera;
import com.papiricoh.pokegame.model.TileMap;

public class GameScreen extends AbstractScreen {

    private TileMap tileMap;
    private Actor player;
    private PlayerController controller;
    private Camera camera;

    private SpriteBatch batch;
    private Texture characterStanding;

    public GameScreen(PokeGame app) {
        super(app);

        characterStanding = new Texture("player/player_texture.png");
        batch = new SpriteBatch();
        tileMap = new TileMap(20, 20);
        player = new Actor(tileMap, 1, 1);
        controller = new PlayerController(player);
        camera = new Camera();
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
        player.update(delta);
        camera.update(player.getWorldX()+0.5f, player.getWorldY()+0.5f);
        batch.begin();

        float worldStartX = (float) Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = (float) Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        int mapWidth = tileMap.getWidth();
        int mapHeight = tileMap.getHeight();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                batch.draw(tileMap.getTile(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
            }
        }

        batch.draw(characterStanding, worldStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE, worldStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
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
