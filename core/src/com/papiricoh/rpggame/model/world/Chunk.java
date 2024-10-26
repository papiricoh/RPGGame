package com.papiricoh.rpggame.model.world;


import com.papiricoh.rpggame.model.world.objects.WorldObject;
import com.papiricoh.rpggame.model.world.perlin.PerlinMap;

public class Chunk {
    public static final int CHUNK_SIZE = 16;
    static float perlinScale = 0.01f;

    private int chunkX;
    private int chunkY;

    private Tile[][] tiles;
    private Tile[][] superior_tiles;
    private Tile[][] building_tiles;
    private WorldObject[][] worldObjects;

    public Chunk(int chunkX, int chunkY, PerlinMap perlinMap) {
        tiles = new Tile[CHUNK_SIZE][CHUNK_SIZE];
        building_tiles = new Tile[CHUNK_SIZE][CHUNK_SIZE];
        superior_tiles = new Tile[CHUNK_SIZE][CHUNK_SIZE];
        worldObjects = new WorldObject[CHUNK_SIZE][CHUNK_SIZE];

        this.chunkX = chunkX;
        this.chunkY = chunkY;
        generateChunk(chunkX, chunkY, perlinMap);
    }

    private void generateChunk(int chunkX, int chunkY, PerlinMap perlinMap) {
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                int globalX = chunkX * CHUNK_SIZE + x;
                int globalY = chunkY * CHUNK_SIZE + y;


                float noiseValue = perlinMap.noise(globalX * perlinScale, globalY * perlinScale);
                if(noiseValue > 0.27) {
                    tiles[x][y] = new Tile("terrain/dark_grass.png", true, TileType.FOREST);
                }else if (noiseValue > 0.2) { //CONDICION DE DIBUJADO
                    tiles[x][y] = new Tile("terrain/grass_1.png", true, TileType.LAND);
                }else if(noiseValue > 0.1){
                    tiles[x][y] = new Tile("terrain/water.png", true, TileType.WATER);
                }else {
                    tiles[x][y] = new Tile("terrain/deep_water.png", true, TileType.DEEP_WATER);
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Tile getCenterTile() {
        return tiles[8][8];
    }

    public WorldObject getObject(int x, int y) {
        try {
            return worldObjects[x][y];
        }catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Tile getSuperiorTile(int x, int y) {
        return superior_tiles[x][y];
    }

    public Tile getBuildingTile(int x, int y) {
        return building_tiles[x][y];
    }

    public int getX() {
        return this.chunkX;
    }

    public int getY() {
        return this.chunkY;
    }
}
