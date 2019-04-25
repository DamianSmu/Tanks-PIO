package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PowerBar extends Actor
{
    private TextureRegion texture;

    public PowerBar(Stage stage)
    {
        texture = new TextureRegion(new Texture("power.png"));
        setHeight(10);
        setWidth(100);
        getColor().a = 0;
        stage.addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( isVisible())
            batch.draw( texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public void setPowerLevel(int powPercent)
    {
        getColor().a = 1;
        texture.setRegion(0,0, powPercent, 20);
        setWidth(powPercent);
    }

    public void hide()
    {
        addAction(Actions.fadeOut(1f));
    }
}
