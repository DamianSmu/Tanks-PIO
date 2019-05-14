package com.pio.tanks.Animations;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.Assets;

public class ExplosionAnimation extends AbstractAnimation
{
    public ExplosionAnimation(Stage stage, float posX, float posY)
    {
        super(stage, posX, posY);
        loadAnimation(Assets.EXPL_TEX, 0.12f);
        setSize(90);
    }
}
