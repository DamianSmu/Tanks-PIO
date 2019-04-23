package com.pio.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanksGameClass extends Game
{

	@Override
	public void create()
	{
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor( im );
		setScreen(new GameScreen());
	}
}
