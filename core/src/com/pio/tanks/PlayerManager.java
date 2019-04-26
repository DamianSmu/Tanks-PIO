package com.pio.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

public class PlayerManager
{
    private static Tank tankA;
    private static Tank tankB;
    private static Tank activeTank;
    private int timer;

    public PlayerManager(Stage stage)
    {
        tankA = new Tank(100, 100, stage, true);
        tankA.setPlayerName("Tank A");
        tankB = new Tank(800, 100, stage, false);
        tankB.setPlayerName("Tank B");
        activeTank = tankA;

        timer = 10;
        nextTurnTimerStart();
    }

    public void nextTurn()
    {
        if (activeTank.equals(tankA))
            activeTank = tankB;
        else
            activeTank = tankA;

        activeTank.ableToShoot();
    }

    public void setNextTurnTimer(int seconds)
    {
        timer = seconds;
    }

    private void nextTurnTimerStart()
    {
        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                timer--;
                if (timer == 0)
                {
                    nextTurn();
                    timer = 10;
                }
            }
        }, 1, 1);
    }

    public int getTimerSeconds()
    {
        return timer;
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
