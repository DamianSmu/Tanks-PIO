package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractShell extends Actor
{
    protected TextureRegion texture;
    //protected Rectangle rectangle;
    private Polygon boundaryPolygon;

    private Stage stage;
    private Vector2 velocityVec;
    private Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    public AbstractShell(Stage stage, float posX, float posY, float angle, float acceleration)
    {
        this.stage = stage;
        stage.addActor(this);
        this.setZIndex(1);

        setPosition(posX, posY);

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        this.acceleration = acceleration;
        maxSpeed = 1000;
        deceleration = 0;

        accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));

        setBoundaryPolygon();
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (getY() <= 0)
            remove();

        /* Gravity */
        accelerationVec.add(new Vector2(10, 0).setAngle(270));

        velocityVec.add(accelerationVec.x, accelerationVec.y);

        float speed = velocityVec.len();

        if (accelerationVec.len() == 0)
            speed -= deceleration * delta;

        speed = MathUtils.clamp(speed, 0, maxSpeed);

        setSpeed(speed);

        moveBy(velocityVec.x * delta, velocityVec.y * delta);

        accelerationVec.set(0, 0);
        setRotation(velocityVec.angle());

        detectHit();
    }


    public void setSpeed(float speed)
    {
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public void setBoundaryPolygon()
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0, 0, w, 0, w, h, 0, h};
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    private void detectHit()
    {
        for (Actor actor : stage.getActors())
            if (actor instanceof Tank)
            {
                Tank tank = (Tank) actor;
                if (Intersector.overlapConvexPolygons(getBoundaryPolygon(), tank.getBoundaryPolygon()))
                {
                    tank.decreaseHp(20);
                    if (tank.getHp() <= 0)
                        tank.remove();
                    this.remove();
                }
            }
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
