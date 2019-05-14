package com.pio.tanks;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class StandardShell extends AbstractShell
{
    public StandardShell(Stage stage, float posX, float posY, float angle, float acceleration)
    {
        super(stage, posX, posY, angle, acceleration);

        texture = new TextureRegion(Assets.SHELL_TEX_0);

        addAction(Actions.sizeTo(38, 26, 0.2f));

        getColor().a = 0;
        addAction(Actions.fadeIn(0.2f));
        addAction(Actions.after(Actions.run(this::setBoundaryPolygon)));
    }
}
