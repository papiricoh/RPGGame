package com.papiricoh.rpggame.model.world.miniMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.rpggame.RPGGame;
import com.papiricoh.rpggame.model.Actor;
import com.papiricoh.rpggame.model.world.Tile;
import com.papiricoh.rpggame.util.Assets;

public class MiniMapRenderer {
    private static int worldlength = 64;
    private Tile[][] worldTiles;

    public MiniMapRenderer() {
    }

    private Tile[][] getWorldTiles() {
        Tile[][] tiles = new Tile[worldlength][worldlength];
        Actor player = RPGGame.getGameScreen().getPlayer();

        int mapInitX = player.getX() - ((int) worldlength / 2);
        int mapInitY = player.getY() - ((int) worldlength / 2);


        for (int x = 0; x < worldlength; x++) {
            for (int y = 0; y < worldlength; y++) {
                Tile tile = RPGGame.getGameScreen().getWorldManager().getWorld().getMap().getTile(x + mapInitX, y + mapInitY);
                tiles[x][y] = tile;
            }
        }

        return tiles;
    }

    public void initTiles() {
        this.worldTiles = getWorldTiles();
    }

    public void renderMap(SpriteBatch batch) {
        int tileSize = 4;

        for (int x = 0; x < worldlength; x++) {
            for (int y = 0; y < worldlength; y++) {
                Tile tile = worldTiles[x][y];
                Color tileColor;


                switch (tile.getType()) {
                    case DEEP_WATER:
                        tileColor = Color.BLUE;
                        break;
                    case WATER:
                        tileColor = Color.SKY;
                        break;
                    case LAND:
                        tileColor = Color.GREEN;
                        break;
                    case FOREST:
                        tileColor = Color.FOREST;
                        break;
                    default:
                        tileColor = Color.GRAY;
                }


                batch.setColor(tileColor);
                batch.draw(Assets.pixelTexture, x * tileSize, y * tileSize, tileSize, tileSize);

            }
        }


        batch.setColor(Color.WHITE);
    }
}
