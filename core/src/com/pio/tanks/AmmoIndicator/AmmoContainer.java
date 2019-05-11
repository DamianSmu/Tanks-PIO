package com.pio.tanks.AmmoIndicator;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Iterator;

public class AmmoContainer
{
    private ArrayList<AmmoImage> source;
    private int shells;
    private int type;
    private Table table;

    public AmmoContainer(Table table, int type, int shells)
    {
        this.table = table;
        this.type = type;
        this.shells = shells;

        source = new ArrayList<>();
    }

    public void setOnTable()
    {
        for (int i = 0; i < shells; i++)
        {
            AmmoImage img = new AmmoImage(type);
            table.add(img).top().left();
            source.add(img);
        }
        setUpAnimation();
    }

    public void setUpAnimation()
    {
        Iterator<AmmoImage> iterator = source.iterator();
        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                iterator.next().setupAnimation();
            }
        }, 0.5f, 0.15f, source.size() - 1);
    }

    public void destroyOneShell()
    {
        source.get(source.size() - 1).destroy();
        source.remove(source.size() - 1);
    }
}
