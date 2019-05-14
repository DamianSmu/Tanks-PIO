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

    private Stage st;
    private Vector2 velocityVec;
    private Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    public AbstractShell(Stage stage, float posX, float posY, float angle, float acceleration)
    {
        st=stage;
        stage.addActor(this);
        this.toFront();

        setPosition(posX, posY);

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        this.acceleration = acceleration;
        maxSpeed = 1000;
        deceleration = 0;

        accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));

    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if(getY()<=0)
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
        rotate();
        detectHit();
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

    public void setBoundaryRectangle()
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition( getX(), getY() );
        boundaryPolygon.setOrigin( getOriginX(), getOriginY() );
        boundaryPolygon.setRotation ( getRotation() );
        boundaryPolygon.setScale( getScaleX(), getScaleY() );
        return boundaryPolygon;
    }

    private void detectHit (){
        for (Actor actor : st.getActors())
            if (actor instanceof Tank)
                if ( Intersector.overlapConvexPolygons( this.getBoundaryPolygon(), ((Tank)actor).getBoundaryPolygon())){
                    ((Tank)actor).setHp(((Tank)actor).getHp()-20);
                    if ( ((Tank)actor).getHp()<=0)
                        ((Tank)actor).remove();
                    this.remove();
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
        setBoundaryRectangle();
    }
}
