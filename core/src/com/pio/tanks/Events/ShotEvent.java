package com.pio.tanks.Events;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.pio.tanks.AbstractShell;

public class ShotEvent extends Event
{
    private int shellType;
    private AbstractShell shell;

    public ShotEvent(int shellType, AbstractShell shell)
    {
        this.shellType = shellType;
        this.shell = shell;
    }

    public int getShellType()
    {
        return shellType;
    }

    public AbstractShell getShell()
    {
        return shell;
    }
}
