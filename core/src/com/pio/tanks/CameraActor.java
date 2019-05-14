package com.pio.tanks;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CameraActor extends Actor
{
    private OrthographicCamera camera;
    private int distAbove;
    private Actor followedActor;

    public CameraActor()
    {
        camera = new OrthographicCamera();
        setDefaultPosition(0);
        camera.update();
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        camera.position.set(getX(), getY(), 0);
        if (followedActor != null)
            setPosition(followedActor.getX() + followedActor.getWidth() / 2f, followedActor.getY() + distAbove);

        camera.zoom = getScaleX();

        if (followedActor != null)
        {
            float boundedX = MathUtils.clamp(camera.position.x, 0, camera.viewportWidth);
            camera.position.x = boundedX;
            setX(boundedX);
        }
    }

    public void moveToActor(Actor actor, float zoom, int distAbove)
    {
        this.distAbove = distAbove;
        followedActor = null;
        setZoom(zoom);
        float posX = MathUtils.clamp(actor.getX() + actor.getWidth() / 2f, 0, camera.viewportWidth);
        addAction(Actions.moveTo(posX, actor.getY() + distAbove, 1f, Interpolation.smooth2));
        addAction(Actions.after(Actions.run(() -> followActor(actor, zoom, distAbove))));
    }

    public void followActor(Actor actor, float zoom, int distAbove)
    {
        this.distAbove = distAbove;
        setZoom(zoom);
        followedActor = actor;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public void setStage(Stage stage)
    {
        stage.addActor(this);
    }

    public void setZoom(float zoom)
    {
        addAction(Actions.scaleTo(zoom, zoom, 1f, Interpolation.smooth2));
    }

    public void setDefaultPosition(float actionDuration)
    {
        followedActor = null;
        addAction(Actions.moveTo(500, 600, actionDuration, Interpolation.smooth2));
        addAction(Actions.scaleTo(1.7f, 1.7f, actionDuration, Interpolation.smooth2));
    }
}
