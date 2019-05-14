package com.pio.tanks;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

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
