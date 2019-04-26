package com.pio.tanks;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen implements Screen, InputProcessor
{
    private Stage mainStage;
    private Stage uiStage;
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

    /* User interface */
    private Label hpLabelA;
    private Label hpLabelB;
    private Label activeTankLabel;
    private Table uiTable;
    private Image ammoImage;
    private Label ammoLabel;

    public GameScreen()
    {
        camera = new OrthographicCamera();
        FitViewport viewport = new FitViewport(1000, 800, camera);
        mainStage = new Stage(viewport);
        uiStage = new Stage(viewport);

        playerManager = new PlayerManager(mainStage);

        background = new Background(mainStage);
        background.setPosition(0, 0);
        background.toBack();

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        hpLabelA = new Label("", TanksGameClass.labelStyle);
        hpLabelA.setColor(Color.BLACK);

        hpLabelB = new Label("", TanksGameClass.labelStyle);
        hpLabelB.setColor(Color.BLACK);

        activeTankLabel = new Label("", TanksGameClass.labelStyle);
        activeTankLabel.setColor(Color.BLACK);

        ammoImage = new Image(new Texture("tank_bullet3.png"));
        ammoImage.rotateBy(45);
        ammoImage.setOrigin(ammoImage.getWidth()/2, ammoImage.getHeight()/2);
        ammoImage.scaleBy(-0.25f);

        uiTable.pad(30);
        uiTable.add(hpLabelA).top();
        uiTable.add(activeTankLabel).expandX().top();
        uiTable.add(hpLabelB).top();
        uiTable.row();
        uiTable.add(ammoImage).expandY().top().left();


        uiTable.setDebug(true);
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
        uiStage.act();
        processInput();
        setLabels();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
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
        Tank activeTank = PlayerManager.getActiveTank();

        if (keyLeftPressed)
            activeTank.moveBy(0);
        if (keyRightPressed)
            activeTank.moveBy(1);
        if (keyRotateTurretUpPressed)
            activeTank.rotateTurret(0);
        if (keyRotateTurretDownPressed)
            activeTank.rotateTurret(1);

        if (activeTank.isAbleToShoot())
        {
            if (spacePressTimeEnd > 0.3f)
            {
                activeTank.shot(spacePressTimeEnd);
                activeTank.getPowerBar().hide();
                spacePressTimeEnd = 0;
                playerManager.setNextTurnTimer(3);
            }
            if (spacePressTimeStart > 0)
            {
                int percent = Math.round((MathUtils.clamp(TimeUtils.millis() - spacePressTimeStart, 0, spacePressTimeMax) / (float) spacePressTimeMax) * 100);
                PlayerManager.getActiveTank().getPowerBar().setPowerLevel(percent);
            }
        }
        spacePressTimeEnd = 0;
    }

    public void setLabels()
    {
        hpLabelA.setText("HP: " + PlayerManager.getTankA().getHp());
        hpLabelB.setText("HP: " + PlayerManager.getTankB().getHp());
        activeTankLabel.setText(PlayerManager.getActiveTank().getPlayerName() + "\n" + playerManager.getTimerSeconds());
    }
}
