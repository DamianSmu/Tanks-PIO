package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class Tree extends Actor
{

    private TextureRegion texture;
    private Random random;

    public Tree(Stage stage, int lowerXBoundary, int upperXBoundary)
    {
        random = new Random();
        stage.addActor(this);
        texture = new TextureRegion(Assets.TREE_TEX[random.nextInt(Assets.TREE_TEX.length)]);
        setSize(68, 146);

        setPosition((int)(random.nextDouble() * (upperXBoundary - lowerXBoundary) + lowerXBoundary), random.nextInt(80) + 60);
        setScale(1.8f - getY()/120);

        if(getY() < 105)
            toFront();
        else
            toBack();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (isVisible()) {
            batch.draw(texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
