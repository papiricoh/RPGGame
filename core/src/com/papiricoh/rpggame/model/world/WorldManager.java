package com.papiricoh.rpggame.model.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.rpggame.RPGGame;
import com.papiricoh.rpggame.Settings;
import com.papiricoh.rpggame.model.Actor;
import com.papiricoh.rpggame.model.Camera;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorldManager {
    private World world;

    public WorldManager(World world) {
        this.world = world;

    }


    public void render(SpriteBatch batch, Camera camera) {
        float worldStartX = (float) Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = (float) Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        Actor player = RPGGame.getGameScreen().getPlayer();
        //System.out.println("Player coords: " + player.getX() + "," + player.getY());

        int mapInitX = player.getX() - 20;
        int mapInitY = player.getY() - 20;

        int mapWidth = player.getX() + 20;
        int mapHeight = player.getY() + 20;
        for (int x = mapInitX; x < mapWidth; x++) {
            for (int y = mapInitY; y < mapHeight; y++) {
                batch.draw(world.getMap().getTile(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
            }
        }

        for (int x = mapInitX; x < mapWidth; x++) {
            for (int y = mapInitY; y < mapHeight; y++) {
                if (world.getObjectByCoord(x, y) != null) {
                    batch.draw(world.getObjectByCoord(x, y).getTexture(), worldStartX + x * Settings.SCALED_TILE_SIZE, worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
                }
            }
        }


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Actor player = RPGGame.getGameScreen().getPlayer();
                Chunk playerChunk = world.getMap().getChunkByCoords(player.getX(), player.getY());
                world.getMap().unloadFarChunks(playerChunk.getX(), playerChunk.getY());
            }
        });


    }

    public World getWorld() {
        return this.world;
    }
}
