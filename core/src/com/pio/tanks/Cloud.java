package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class Cloud extends Actor {

    private TextureRegion texture;
    private Random random;
    private float speed;

    public Cloud(Stage stage, int lowerBoundary,int  upperBoundary) {
        random = new Random();
        stage.addActor(this);
        texture = new TextureRegion(Assets.CLOUD_TEX[random.nextInt(4)]);
        speed = (float)(random.nextFloat() / 5 + 0.01);
        if(random.nextBoolean())
            speed = -1 * speed;
        setSize(228, 124);

        setPosition((int)(random.nextDouble() * (upperBoundary - lowerBoundary) + lowerBoundary), 400 + random.nextInt(300));
        toFront();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        setX(getX() + speed);

        if (isVisible()) {
            batch.draw(texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }

    }
}
