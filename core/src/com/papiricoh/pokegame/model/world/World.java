package com.papiricoh.pokegame.model.world;

public class World {

    private int width;
    private int height;
    private TileMap tileMap;

    private WorldObject[][] worldObjects;
    private int worldObjectCounter;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tileMap = new TileMap(width, height);
        this.worldObjects = new WorldObject[width][height];
    }

    public boolean addObject(WorldObject object) {
        if(checkBorders(object.getX(), object.getY()) && this.worldObjects[object.getX()][object.getY()] == null ) {
            worldObjectCounter++;
            this.worldObjects[object.getX()][object.getY()] = object;
            return true;
        }
        return false;
    }

    public boolean moveObject(int origin_x, int origin_y, int dest_x, int dest_y) {
        if(checkBorders(origin_x, origin_y) && checkBorders(dest_x, dest_y) && this.worldObjects[origin_x][origin_y] != null) {
            this.worldObjects[dest_x][dest_y] = this.worldObjects[origin_x][origin_y];
            this.worldObjects[dest_x][dest_y].setCoords(dest_x, dest_y);
            this.worldObjects[origin_x][origin_y] = null;
            return true;
        }
        return false;
    }

    public WorldObject getObjectByCoord(int x, int y) {
        if(checkBorders(x, y) && this.worldObjects[x][y] != null) {
            return this.worldObjects[x][y];
        }
        return null;
    }

    public boolean checkBorders(int x, int y) {
        return x < this.width && y < this.height;
    }

    public TileMap getMap() {
        return this.tileMap;
    }

    public void deleteObjectByCoord(int x, int y) {
        worldObjectCounter--;
        this.worldObjects[x][y] = null;
    }

    public int getWorldObjectCounter() {
        return worldObjectCounter;
    }
}
