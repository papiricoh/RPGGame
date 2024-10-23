package com.papiricoh.pokegame.model.world.worldMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.papiricoh.pokegame.PokeGame;
import com.papiricoh.pokegame.Settings;
import com.papiricoh.pokegame.model.Actor;
import com.papiricoh.pokegame.model.world.Tile;
import com.papiricoh.pokegame.util.Assets;

public class WorldMapRenderer {
    private static int worldlength = 64;
    private Tile[][] worldTiles;

    public WorldMapRenderer() {
        this.worldTiles = getWorldTiles();
    }

    private Tile[][] getWorldTiles() {
        Tile[][] tiles = new Tile[worldlength][worldlength];
        Actor player = PokeGame.getGameScreen().getPlayer();

        int mapInitX = player.getX() - ((int) worldlength / 2);
        int mapInitY = player.getY() - ((int) worldlength / 2);


        for (int x = 0; x < worldlength; x++) {
            for (int y = 0; y < worldlength; y++) {
                Tile tile = PokeGame.getGameScreen().getWorldManager().getWorld().getMap().getTile(x + mapInitX, y + mapInitY);
                tiles[x][y] = tile;
            }
        }

        return tiles;
    }

    public void renderMap(SpriteBatch batch) {
        int tileSize = 4;

        for (int x = 0; x < worldlength; x++) {
            for (int y = 0; y < worldlength; y++) {
                Tile tile = worldTiles[x][y];
                Color tileColor;


                switch (tile.getType()) {
                    case WATER:
                        tileColor = Color.BLUE;
                        break;
                    case LAND:
                        tileColor = Color.GREEN;
                        break;
                    case FOREST:
                        tileColor = Color.DARK_GRAY;
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
