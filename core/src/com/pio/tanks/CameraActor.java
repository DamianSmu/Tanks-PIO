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
    private boolean centeredAtActor;
    private Actor followedActor;

    public CameraActor()
    {
        camera = new OrthographicCamera();
        distAbove = 150;
        centeredAtActor = true;
        float posX = 500;
        float posY = 300;
        setScale(1.5f);
        camera.position.set(posX, posY + distAbove, 0);
        setPosition(posX, posY + distAbove);
        camera.update();
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        camera.position.set(getX(), getY() + distAbove,0);
        if(centeredAtActor && followedActor != null)
            setPosition(followedActor.getX() + followedActor.getWidth()/2f, followedActor.getY() + distAbove);
        
        camera.position.x = MathUtils.clamp(camera.position.x, 0, camera.viewportWidth);
        camera.zoom = getScaleX();
    }

    public void moveToActor(Actor actor)
    {
        centeredAtActor = false;
        followedActor = null;
        addAction(Actions.moveTo(actor.getX() + actor.getWidth()/2f, actor.getY() + distAbove, 1.5f, Interpolation.smooth));
        addAction(Actions.after(Actions.run(() -> centeredAtActor = true)));
    }

    public void setCameraPosition(float x, float y, float zoom)
    {
        camera.position.set(x, y, 0);
        camera.zoom = zoom;
        camera.update();
    }
    public void followActor(Actor actor)
    {
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

    public void setDefaultZoom()
    {
        addAction(Actions.scaleTo(1f, 1f, 1f, Interpolation.smooth));
    }
}
