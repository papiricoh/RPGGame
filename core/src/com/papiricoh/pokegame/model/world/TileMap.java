package com.papiricoh.pokegame.model.world;

import com.papiricoh.pokegame.model.world.perlin.PerlinMap;

import java.util.Random;

public class TileMap {
    private int width, height;
    private Tile[][] tiles;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];

        PerlinMap perlinMap = new PerlinMap(new Random().nextInt(100000000));

        for (int i = 0; i < width; i++) { //i = x_coord
            for (int j = 0; j < height; j++) { //j = y_coord
                float noiseValue = perlinMap.noise(i * 0.01f, j * 0.01f);
                if (noiseValue > 0.2) { //CONDICION DE DIBUJADO
                    tiles[i][j] = new Tile("terrain/grass_1.png", true, TileType.LAND);
                }else {
                    tiles[i][j] = new Tile("terrain/water.png", false, TileType.WATER);
                }
            }
        }
    }
    public TileMap(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;

    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
