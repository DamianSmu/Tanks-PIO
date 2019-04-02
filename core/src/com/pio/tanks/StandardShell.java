package com.pio.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class StandardShell extends AbstractShell
{
    public StandardShell(Stage stage, float posX, float posY, float angle, float acceleration)
    {
        super(stage, posX, posY, angle, acceleration);
        texture = new TextureRegion(new Texture("tank_bullet3.png"));

        addAction(Actions.sizeTo(38, 26, 0.3f));
        /* Set alpha to 0 */
        getColor().a = 0;
        addAction(Actions.fadeIn(0.5f));
    }
}
