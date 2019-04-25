package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractShell extends Actor
{
    protected TextureRegion texture;
    protected Rectangle rectangle;

    private Vector2 velocityVec;
    private Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    public AbstractShell(Stage stage, float posX, float posY, float angle, float acceleration)
    {
        stage.addActor(this);
        this.toFront();

        setPosition(posX, posY);

        velocityVec = new Vector2(0,0);
        accelerationVec = new Vector2(0, 0);
        this.acceleration = acceleration;
        maxSpeed = 10000;
        deceleration = 0;

        accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle) );

    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        /* Gravity */
        accelerationVec.add(new Vector2(400, 0).setAngle(270) );

        velocityVec.add( accelerationVec.x * delta, accelerationVec.y * delta );

        float speed = velocityVec.len();

        if (accelerationVec.len() == 0)
            speed -= deceleration * delta;

        speed = MathUtils.clamp(speed, 0, maxSpeed);

        setSpeed(speed);

        moveBy( velocityVec.x * delta, velocityVec.y * delta );
        rectangle.setPosition(getX(),getY());

        /*if (rectangle.overlaps(PlayerManager.getActiveTank().rectangle)) {
            PlayerManager.getActiveTank().remove();
            this.remove();
        }*/

        accelerationVec.set(0,0);

        rotate();
    }


    public void setSpeed(float speed)
    {
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }


    public float getMotionAngle()
    {
        return velocityVec.angle();
    }

    private void rotate()
    {
        setRotation(getMotionAngle());
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
