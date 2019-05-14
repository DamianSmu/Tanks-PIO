package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class BasicAnimation extends Actor
{
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    public BasicAnimation(Stage stage, float posX, float posY, Texture[] source, float frameDuration)
    {
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        
        stage.addActor(this);
        setPosition(posX, posY);
        addAction(Actions.sizeTo(41, 41, 0.1f));

        int frames = source.length;

        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int n = 0; n < frames; n++)
        {
            Texture texture = source[n];
            textureArray.add(new TextureRegion(texture));
        }

        animation = new Animation<TextureRegion>(frameDuration, textureArray);

        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }


    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (animation != null && isVisible())
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX() - getWidth() / 2f, getY() - getHeight() / 2f, getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (!animationPaused)
            elapsedTime += delta;

        if (animation.isAnimationFinished(elapsedTime))
            destroy();

    }

    private void destroy()
    {
        addAction(Actions.fadeOut(0.2f));
        addAction(Actions.after(Actions.removeActor()));
    }
}
