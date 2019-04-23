package com.pio.tanks;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.sql.Time;

public class GameScreen implements Screen, InputProcessor
{
    private Stage mainStage;
    private OrthographicCamera camera;
    private PlayerManager playerManager;

    /* Pressed keys */
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    private boolean keyRotateTurretUpPressed = false;
    private boolean keyRotateTurretDownPressed = false;
    private long spacePressTimeStart;
    private long spacePressTimeEnd;
    private int spacePressTimeMax = 3000;

    /* Actors */
    private Background background;


    /* Power */
    private Texture power;

    public GameScreen()
    {
        camera = new OrthographicCamera();
        mainStage = new Stage(new FitViewport(1000, 800, camera));

        playerManager = new PlayerManager(mainStage);

       background = new Background(mainStage);
       background.setPosition(0, 0);
       background.toBack();

       power = new Texture("power.png");
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

        if(spacePressTimeStart > 0)
        {
            mainStage.getBatch().begin();
            int precent = Math.round((MathUtils.clamp(TimeUtils.millis() - spacePressTimeStart, 0, spacePressTimeMax)/(float)spacePressTimeMax) * 100);
            TextureRegion powerRegion = new TextureRegion(power,precent , 20 );


            mainStage.getBatch().draw(powerRegion, 100, 80);
            mainStage.getBatch().end();
        }
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
                spacePressTimeStart = TimeUtils.millis();
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
            case Input.Keys.SPACE:
                spacePressTimeEnd = TimeUtils.millis() - spacePressTimeStart;
                MathUtils.clamp(spacePressTimeEnd, 0, spacePressTimeMax);
                spacePressTimeStart = 0;
                break;
        }

        return false;
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

    private void processInput()
    {
        if (keyLeftPressed)
            playerManager.getActiveTank().moveBy(0);
        if (keyRightPressed)
            playerManager.getActiveTank().moveBy(1);
        if (keyRotateTurretUpPressed)
            playerManager.getActiveTank().rotateTurret(0);
        if (keyRotateTurretDownPressed)
            playerManager.getActiveTank().rotateTurret(1);
        if(spacePressTimeEnd > 0.3f)
        {
            playerManager.getActiveTank().shot(spacePressTimeEnd);
            spacePressTimeEnd = 0;
            playerManager.nextTurn();
        }
    }
}
