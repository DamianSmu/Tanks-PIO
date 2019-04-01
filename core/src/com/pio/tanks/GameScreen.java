package com.pio.tanks;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen, InputProcessor
{
    private OrthographicCamera camera;

    /* Pressed keys */
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    private boolean keyRotateTurretUpPressed = false;
    private boolean keyRotateTurretDownPressed = false;

    /* Actors */
    private Tank tank;
    private Background background;

    /* Actor container */
    private Stage mainStage;

    public GameScreen()
    {
        camera = new OrthographicCamera();
        mainStage = new Stage(new FitViewport(1000, 800, camera));

        background = new Background(mainStage);

        tank = new Tank(200, 200, mainStage);
    }

    @Override
    public void show()
    {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(mainStage);
    }

    @Override
    public void render(float delta)
    {
        mainStage.act();
        processInput();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.D:
                keyRightPressed = true;
                break;
            case Input.Keys.A:
                keyLeftPressed = true;
                break;
            case Input.Keys.R:
                keyRotateTurretUpPressed = true;
                break;
            case Input.Keys.F:
                keyRotateTurretDownPressed = true;
                break;
            case Input.Keys.SPACE:
                tank.shoot();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case Input.Keys.D:
                keyRightPressed = false;
                break;
            case Input.Keys.A:
                keyLeftPressed = false;
                break;
            case Input.Keys.R:
                keyRotateTurretUpPressed = false;
                break;
            case Input.Keys.F:
                keyRotateTurretDownPressed = false;
                break;
        }
        return false;
    }

    private void processInput()
    {
        if (keyLeftPressed)
            tank.moveBy(0);
        if (keyRightPressed)
            tank.moveBy(1);
        if (keyRotateTurretUpPressed)
            tank.rotateTurret(0);
        if (keyRotateTurretDownPressed)
            tank.rotateTurret(1);
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}

