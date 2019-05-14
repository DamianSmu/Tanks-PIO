package com.pio.tanks.Events;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.pio.tanks.Tank;

public class HitDetectedEvent extends Event
{
    private Tank tank;

    public HitDetectedEvent(Tank tank)
    {
        this.tank = tank;
    }

    public Tank getTank()
    {
        return tank;
    }
}
