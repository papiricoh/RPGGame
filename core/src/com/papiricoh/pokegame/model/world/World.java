package com.papiricoh.pokegame.model.world;

public class World {

    private int width;
    private int height;
    private TileMap tileMap;

    private WorldObject[][] worldObjects;

    private World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tileMap = new TileMap(width, height);
        this.worldObjects = new WorldObject[width][height];
    }

    public boolean addObject(WorldObject object, int x, int y) {
        if(checkBorders(x, y) && this.worldObjects[x][y] == null ) {
            this.worldObjects[x][y] = object;
            return true;
        }
        return false;
    }

    public boolean moveObject(int origin_x, int origin_y, int dest_x, int dest_y) {
        if(checkBorders(origin_x, origin_y) && checkBorders(dest_x, dest_y) && this.worldObjects[origin_x][origin_y] != null) {
            this.worldObjects[dest_x][dest_y] = this.worldObjects[origin_x][origin_y];
            this.worldObjects[origin_x][origin_y] = null;
            return true;
        }
        return false;
    }

    public boolean checkBorders(int x, int y) {
        return x < this.width && y < this.height;
    }

    public TileMap getMap() {
        return this.tileMap;
    }

}
