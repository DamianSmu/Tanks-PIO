package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Event;

public class ShotEvent extends Event
{
    private int shellType;

    public ShotEvent(int shellType)
    {
        this.shellType = shellType;
    }

    public int getShellType()
    {
        return shellType;
    }
}
