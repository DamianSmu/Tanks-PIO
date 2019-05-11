package com.pio.tanks.AmmoIndicator;


import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pio.tanks.Assets;

public class AmmoImage extends Image
{
    public AmmoImage(int ammoType)
    {
        switch (ammoType)
        {
            case 0:
                setDrawable(new TextureRegionDrawable(Assets.SHELL_TEX_0));
                break;
            case 1:
                setDrawable(new TextureRegionDrawable(Assets.SHELL_TEX_1));
                break;
        }
        setSize(38, 26);
        rotateBy(45);
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setScale(0);
    }

    public void destroy()
    {
        addAction(Actions.scaleTo(0, 0, 0.5f, Interpolation.circleOut));
        addAction(Actions.after(Actions.alpha(0)));
    }

    public void setupAnimation()
    {
        addAction(Actions.scaleTo(0.75f,0.75f,0.5f, Interpolation.smooth2));
    }
}
