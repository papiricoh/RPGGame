package com.papiricoh.pokegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.papiricoh.pokegame.screen.GameScreen;

public class PokeGame extends Game {
	private GameScreen screen;
	private AssetManager assetManager;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		assetManager.load("player/playerTextures.atlas", TextureAtlas.class);
		assetManager.finishLoading();

		screen = new GameScreen(this);
		this.setScreen(screen);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
	}
	
	@Override
	public void dispose () {
		screen.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}
}
