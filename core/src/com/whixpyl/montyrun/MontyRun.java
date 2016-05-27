package com.whixpyl.montyrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MontyRun extends Game {
	SpriteBatch batch;
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new ScreenManager(this, 0, -1, -1));
	}

	@Override
	public void render () {
		super.render();
	}
}
