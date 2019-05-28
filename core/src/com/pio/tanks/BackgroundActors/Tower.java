package com.pio.tanks.BackgroundActors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.Assets;

import java.util.Random;

public class Tower extends Actor
{
    private Polygon boundaryPolygon;
    private TextureRegion texture;

    public Tower(Stage stage)
    {
        stage.addActor(this);
        texture = new TextureRegion(Assets.TOWER_TEX);
        setSize(66, 227);

        setPosition(500, 100);
        setBoundaryPolygon(8);
    }

    public Tower(int x, int y)
    {
        setSize(x, y);

        setPosition(500, 100);
        setBoundaryPolygon(8);
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
}
