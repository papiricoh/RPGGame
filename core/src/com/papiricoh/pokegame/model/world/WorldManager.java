package com.papiricoh.pokegame.model.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.pokegame.Settings;
import com.papiricoh.pokegame.model.Camera;

public class WorldManager {
    private World world;

    public WorldManager(World world) {
        this.world = world;

    }

    public void render(SpriteBatch batch, Camera camera) {
        float worldStartX = (float) Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = (float) Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        int mapWidth = world.getMap().getWidth();
        int mapHeight = world.getMap().getHeight();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                batch.draw(world.getMap().getTile(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (world.getObjectByCoord(x, y) != null) {
                    batch.draw(world.getObjectByCoord(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
                }
            }
        }
    }

    public World getWorld() {
        return this.world;
    }
}
