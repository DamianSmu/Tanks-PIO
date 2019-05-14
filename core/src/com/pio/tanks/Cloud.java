package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class Cloud extends Actor
{

    private TextureRegion texture;
    private float speed;

    public Cloud(Stage stage, int lowerBoundary, int upperBoundary)
    {
        Random random = new Random();
        stage.addActor(this);
        texture = new TextureRegion(Assets.CLOUD_TEX[random.nextInt(4)]);
        speed =  (random.nextFloat() / 5f) + 0.01f;
        if (random.nextBoolean())
            speed *= -1f;
        setSize(228, 124);

        setPosition(random.nextFloat() * (upperBoundary - lowerBoundary) + lowerBoundary, 400 + random.nextInt(300));
        toFront();
    }

    @Override
    public void act(float delta)
    {
        moveBy(speed, 0);

        if (getX() < -600)
            setX(2300);
        else if (getX() > 2300)
            setX(-600);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (isVisible())
        {
            batch.draw(texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }

    }
}
