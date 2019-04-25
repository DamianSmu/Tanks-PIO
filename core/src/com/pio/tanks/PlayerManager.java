package com.pio.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

public class PlayerManager
{
    private static Tank tankA;
    private static Tank tankB;
    private static Tank activeTank;

    public PlayerManager(Stage stage)
    {
        tankA = new Tank(100, 100, stage, true);
        tankA.setPlayerName("Tank A");
        tankB = new Tank(800, 100, stage, false);
        tankB.setPlayerName("Tank B");
        activeTank = tankA;
    }

    public void nextTurn()
    {
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                if(activeTank.equals(tankA))
                    activeTank = tankB;
                else
                    activeTank = tankA;
            }
        }, 3);
    }

    public static Tank getActiveTank()
    {
        return activeTank;
    }

    public static Tank getTankA()
    {
        return tankA;
    }

    public static Tank getTankB()
    {
        return tankB;
    }
}
