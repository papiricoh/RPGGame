package com.papiricoh.pokegame.model.world;


import com.papiricoh.pokegame.model.world.perlin.PerlinMap;

public class Chunk {
    public static final int CHUNK_SIZE = 16;
    static float perlinScale = 0.01f;
    private Tile[][] tiles;

    public Chunk(int chunkX, int chunkY, PerlinMap perlinMap) {
        tiles = new Tile[CHUNK_SIZE][CHUNK_SIZE];
        generateChunk(chunkX, chunkY, perlinMap);
    }

    private void generateChunk(int chunkX, int chunkY, PerlinMap perlinMap) {
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                float noiseValue = perlinMap.noise((chunkX * CHUNK_SIZE + x) * perlinScale, (chunkY * CHUNK_SIZE + y) * perlinScale);
                if(noiseValue > 0.27) {
                    tiles[x][y] = new Tile("terrain/dark_grass.png", true, TileType.FOREST);
                }else if (noiseValue > 0.2) { //CONDICION DE DIBUJADO
                    tiles[x][y] = new Tile("terrain/grass_1.png", true, TileType.LAND);
                }else {
                    tiles[x][y] = new Tile("terrain/water.png", true, TileType.WATER);
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
}
