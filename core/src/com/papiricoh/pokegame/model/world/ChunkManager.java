package com.papiricoh.pokegame.model.world;

import com.papiricoh.pokegame.model.world.perlin.PerlinMap;

import java.util.HashMap;
import java.util.Random;

public class ChunkManager {
    private static int mainSeed = 100;
    static PerlinMap perlinMap;
    private HashMap<String, Chunk> loadedChunks;

    public ChunkManager() {
        mainSeed = new Random().nextInt(10000000);
        loadedChunks = new HashMap<>();
        this.perlinMap = new PerlinMap(mainSeed);
    }

    public Chunk getChunk(int chunkX, int chunkY) {
        String chunkKey = chunkX + "," + chunkY;
        if (!loadedChunks.containsKey(chunkKey)) {
            loadedChunks.put(chunkKey, new Chunk(chunkX, chunkY, perlinMap));
        }
        return loadedChunks.get(chunkKey);
    }

    public void unloadChunk(int chunkX, int chunkY) {
        String chunkKey = chunkX + "," + chunkY;
        loadedChunks.remove(chunkKey); // Liberar el chunk si está demasiado lejos
    }

    public Tile getTile(int worldX, int worldY) {
        int chunkX = worldX / Chunk.CHUNK_SIZE;
        int chunkY = worldY / Chunk.CHUNK_SIZE;

        int tileX = worldX % Chunk.CHUNK_SIZE;
        int tileY = worldY % Chunk.CHUNK_SIZE;

        if (tileX < 0) tileX += Chunk.CHUNK_SIZE;
        if (tileY < 0) tileY += Chunk.CHUNK_SIZE;

        Chunk chunk = getChunk(chunkX, chunkY);

        return chunk.getTile(tileX, tileY);
    }

}
