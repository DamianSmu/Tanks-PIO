package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Tank extends Group
{
    private TextureRegion texture;
    private Turret turret;
    private Stage stage;

    public Tank(float posX, float posY, Stage stage)
    {
        super();
        this.stage = stage;

        stage.addActor(this);
        texture = new TextureRegion(new Texture("tanks_tankGreen_body1.png"));
        setPosition(posX, posY);
        setWidth(83);
        setHeight(49);

        turret = new Turret(stage);
        addActor(turret);
        turret.setPosition(42, 37);
        turret.toFront();

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
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

   public void moveBy(int moveDirection)
   {
       if (moveDirection == 0)
           moveBy(-2, 0);
       if (moveDirection == 1)
           moveBy(2, 0);
   }
   public void rotateTurret(int rotateDirection)
   {
       if(rotateDirection == 0)
           turret.rotateBy(2);
       if(rotateDirection == 1)
           turret.rotateBy(-2);
   }

   public void shoot()
   {
        Vector2 pos = turret.localToStageCoordinates(new Vector2(turret.getWidth()/2f, turret.getHeight()/2f));
       new StandardShell(stage,pos.x,pos.y, turret.getRotation(), 20000);
   }
}
