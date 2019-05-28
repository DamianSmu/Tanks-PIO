package com.pio.tanks.BackgroundActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pio.tanks.Animations.ExplosionAnimation;
import com.pio.tanks.Assets;
import com.pio.tanks.Events.HitDetectedEvent;
import com.pio.tanks.Tank;

import java.util.Random;

public class Tree extends Actor
{
    private Polygon boundaryPolygon;
    private TextureRegion texture;
    private Random random;
    private  Stage stage;
    private boolean isFallen;

    public Tree(Stage stage, int lowerXBoundary, int upperXBoundary)
    {
        this.stage = stage;
        random = new Random();
        stage.addActor(this);
        texture = new TextureRegion(Assets.TREE_TEX[random.nextInt(Assets.TREE_TEX.length)]);
        setSize(68, 146);

        isFallen = false;

        setPosition((int)(random.nextDouble() * (upperXBoundary - lowerXBoundary) + lowerXBoundary), random.nextInt(80) + 60);
        setScale(1.8f - getY()/120);
        setOrigin(getWidth()/2f, 0);

        if(getY() < 105)
            toFront();
        else
            toBack();
        setBoundaryPolygon(8);
    }

    public Tree(int lowerXBoundary, int upperXBoundary)
    {
        random = new Random();
        setSize(68, 146);

        isFallen = false;

        setPosition((int)(random.nextDouble() * (upperXBoundary - lowerXBoundary) + lowerXBoundary), random.nextInt(80) + 60);
        setScale(1.8f - getY()/120);
        setOrigin(getWidth()/2f, 0);

    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    public void setBoundaryPolygon(int numSides)
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2 * numSides];
        for (int i = 0; i < numSides; i++)
        {
            float angle = i * 6.28f / numSides;

            vertices[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;

            vertices[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
        }
        boundaryPolygon = new Polygon(vertices);
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

    @Override
    public void act(float delta)
    {
        super.act(delta);

        for (Actor actor : stage.getActors())
        {
            if (actor instanceof Tank)
            {
                Tank tank = (Tank) actor;
                if (Intersector.overlapConvexPolygons(getBoundaryPolygon(), tank.getBoundaryPolygon()) && !isFallen)
                {
                    isFallen = true;
                    int rotateDir = tank.getX() > getX() ? 90 : -90;
                    addAction(Actions.rotateBy(rotateDir, 0.5f, Interpolation.circleIn));
                }
            }
        }
    }


}
