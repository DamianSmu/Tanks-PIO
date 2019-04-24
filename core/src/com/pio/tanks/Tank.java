package com.pio.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

public class Tank extends Group
{
    private TextureRegion texture;
    public Rectangle rectangle;
    private Turret turret;
    private Stage stage;
    private  boolean isRight;

    public Tank(float posX, float posY, Stage stage, boolean isRight)
    {
        super();
        this.stage = stage;

        stage.addActor(this);
        texture = new TextureRegion(new Texture("tanks_tankGreen_body1.png"));
        setPosition(posX, posY);
        setWidth(83);
        setHeight(49);

        rectangle = new Rectangle(getX(),getY(),getWidth(),getHeight());

        turret = new Turret(stage);
        addActor(turret);
        turret.setPosition(42, 37);
        turret.setOrigin(0, turret.getHeight()/2f);
        turret.toFront();


        if(!isRight)
            changeToLeft();
    }

    private void changeToLeft()
    {
        texture.flip(true,false);
        turret.setRotation(180);
    }


    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (isVisible())
        {
            batch.draw(texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
        }

    }

    public void moveBy(int moveDirection)
    {
        if (moveDirection == 0)
            moveBy(-2, 0);
        if (moveDirection == 1)
            moveBy(2, 0);
        rectangle.setPosition(getX(),getY());
    }
    public void rotateTurret(int rotateDirection)
    {
        if(rotateDirection == 0 /*&& turret.getRotation() <= 90f*/)
            turret.rotateBy(2);
        if(rotateDirection == 1 /*&& turret.getRotation() >= 0f*/)
            turret.rotateBy(-2);
    }

    public void shot(float acceleration/* BulletType */)
    {
        Vector2 pos = turret.localToStageCoordinates(new Vector2(turret.getWidth()/2f, turret.getHeight()/2f));
        new StandardShell(stage,pos.x,pos.y, turret.getRotation(), 25 * acceleration);
    }
}
