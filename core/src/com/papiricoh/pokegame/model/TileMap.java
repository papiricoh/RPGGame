package com.papiricoh.pokegame.model;

import java.util.Random;

public class TileMap {
    private int width, height;
    private Tile[][] tiles;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) { //i = x_coord
            for (int j = 0; j < height; j++) { //j = y_coord
                if (new Random().nextInt(0,2) == 1) { //CONDICION DE DIBUJADO
                    tiles[i][j] = new Tile("terrain/grass_1.png");
                }else {
                    tiles[i][j] = new Tile("terrain/grass_2.png");
                }
            }
        }
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
