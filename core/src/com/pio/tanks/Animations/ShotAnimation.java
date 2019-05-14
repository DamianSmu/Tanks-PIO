package com.pio.tanks.Animations;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.Assets;

public class ShotAnimation extends AbstractAnimation
{

    public ShotAnimation(Stage stage, float posX, float posY)
    {
        super(stage, posX, posY);
        loadAnimation(Assets.SHOT_TEX, 0.10f);
        setSize(40);
    }
}
