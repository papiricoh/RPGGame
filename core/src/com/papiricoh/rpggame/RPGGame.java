package com.papiricoh.rpggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.papiricoh.rpggame.screen.GameScreen;
import com.papiricoh.rpggame.screen.TitleScreen;
import com.papiricoh.rpggame.util.Assets;
import com.papiricoh.rpggame.util.SkinGenerator;

public class RPGGame extends Game {
	private static GameScreen gameScreen;
	private TitleScreen title;
	private AssetManager assetManager;


	private Skin skin;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		assetManager.load("player/playerTextures.atlas", TextureAtlas.class);
		assetManager.load("ui/uipack.atlas", TextureAtlas.class);
		assetManager.load("font/small_letters_font.fnt", BitmapFont.class);
		assetManager.finishLoading();

		skin = SkinGenerator.generateSkin(assetManager);

		gameScreen = new GameScreen(this);
		this.setScreen(gameScreen);
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
	}
	
	@Override
	public void dispose () {
		screen.dispose();
		//title.dispose();
		skin.dispose();
		assetManager.dispose();

		Assets.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}
}
