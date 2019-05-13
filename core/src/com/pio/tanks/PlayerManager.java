package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.pio.tanks.AmmoIndicator.AmmoIndicatorController;

public class PlayerManager
{
    private static Tank tankA;
    private static Tank tankB;
    private static Tank activeTank;
    private int timer;
    private Stage stage;
    private CameraActor camera;
    private AmmoIndicatorController ammoController;

    public PlayerManager(Stage stage, CameraActor camera)
    {
        this.stage = stage;
        this.camera = camera;
        tankA = new Tank(100, 100, stage, true);
        tankA.setPlayerName("Tank A");
        tankB = new Tank(800, 100, stage, false);
        tankB.setPlayerName("Tank B");
        activeTank = tankA;

        timer = 15;

        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                nextTurnTimerStart();
                camera.moveToActor(activeTank,1f, 300);
            }
        }, 2);

        tankA.addListener(event -> {

            if (event instanceof ShotEvent)
            {
                ammoController.removeShell('A', ((ShotEvent) event).getShellType());
                camera.setDefaultPosition(0.8f);
                return true;
            }
            else return false;
        });

        tankB.addListener(event -> {

            if (event instanceof ShotEvent)
            {
                ammoController.removeShell('B', ((ShotEvent) event).getShellType());
                camera.setDefaultPosition(0.8f);
                return true;
            }
            else return false;
        });
    }

    public void nextTurn()
    {
        if (activeTank.equals(tankA))
            activeTank = tankB;
        else
            activeTank = tankA;

        activeTank.ableToShoot();
        camera.moveToActor(getActiveTank(), 1f, 300);
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
                    timer = 15;
                }
            }
        }, 1, 1);
    }


    public int getTimerSeconds()
    {
        return timer;
    }

    public Tank getActiveTank()
    {
        return activeTank;
    }

    public Tank getInactiveTank()
    {
        if (activeTank.equals(tankA))
            return tankB;
        else
            return tankA;
    }

    public Tank getTankA()
    {
        return tankA;
    }

    public Tank getTankB()
    {
        return tankB;
    }

    public void setAmmoController(AmmoIndicatorController ammoController)
    {
        this.ammoController = ammoController;
    }
}
