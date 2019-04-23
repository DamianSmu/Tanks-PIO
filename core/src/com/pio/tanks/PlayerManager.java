package com.pio.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PlayerManager
{
    private Tank tank1;
    private Tank tank2;
    private Tank activeTank;

    public PlayerManager(Stage stage)
    {
        tank1 = new Tank(100, 100, stage, true);
        tank2 = new Tank(700, 100, stage, false);
        activeTank = tank1;
    }

    public void nextTurn()
    {
        if(activeTank.equals(tank1))
            activeTank = tank2;
        else
            activeTank = tank1;
    }

    public Tank getActiveTank()
    {
        return activeTank;
    }
}
