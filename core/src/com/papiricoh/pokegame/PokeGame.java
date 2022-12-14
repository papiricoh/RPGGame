package com.papiricoh.pokegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.papiricoh.pokegame.screen.GameScreen;

public class PokeGame extends Game {
	private GameScreen screen;
	
	@Override
	public void create () {
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
}
