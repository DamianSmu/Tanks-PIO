package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Random;

public class CloudManager
{

    private final int NUMBER = 15;
    ArrayList<Cloud> clouds;

    public CloudManager(Stage stage)
    {
        clouds = new ArrayList<>();
        for (int i = 0; i < NUMBER; i++)
        {
            clouds.add(new Cloud(stage, -500, 1000));
        }
    }

}
