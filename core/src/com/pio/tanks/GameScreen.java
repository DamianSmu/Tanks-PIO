package com.pio.tanks;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pio.tanks.AmmoIndicator.AmmoIndicatorController;


public class GameScreen extends InputProcessorAdapter implements Screen
{
    private Stage mainStage;
    private Stage uiStage;
    private CameraActor cameraActor;
    private PlayerManager playerManager;
    private BackgroundManager backgroundManager;

    /* Pressed keys */
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    private boolean keyRotateTurretUpPressed = false;
    private boolean keyRotateTurretDownPressed = false;
    private long spacePressTimeStart;
    private long spacePressTimeEnd;
    private int spacePressTimeMax = 3000;


    /* User interface */
    private Label hpLabelA;
    private Label hpLabelB;
    private Label activeTankLabel;
    private Label timerLabel;
    private Table uiTable;

    public GameScreen()
    {
        cameraActor = new CameraActor();
        FitViewport mainStageViewport = new FitViewport(1000, 800, cameraActor.getCamera());
        FitViewport uiStageViewport = new FitViewport(1000, 800);
        mainStage = new Stage(mainStageViewport);
        uiStage = new Stage(uiStageViewport);
        cameraActor.setStage(mainStage);


        playerManager = new PlayerManager(mainStage, cameraActor);
        backgroundManager = new BackgroundManager(mainStage);


        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        hpLabelA = new Label("", Assets.FONT);


        hpLabelB = new Label("", Assets.FONT);


        activeTankLabel = new Label("", Assets.FONT);


        timerLabel = new Label("", Assets.FONT);


        uiTable.pad(25);
        uiTable.add(hpLabelA).colspan(5).top();
        uiTable.add(activeTankLabel).top().expandX();
        uiTable.add(hpLabelB).top().colspan(5);
        uiTable.row();
        playerManager.setAmmoController(new AmmoIndicatorController(uiTable));
        uiTable.row();
        uiTable.add(timerLabel).colspan(11).bottom().expandY();

        //uiTable.setDebug(true);
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
                spacePressTimeEnd = MathUtils.clamp(spacePressTimeEnd, 0, spacePressTimeMax);
                spacePressTimeStart = 0;
                break;
        }
        return false;
    }

    private void processInput()
    {
        Tank activeTank = playerManager.getActiveTank();

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
                playerManager.getActiveTank().getPowerBar().setPowerLevel(percent);
            }
        }
        spacePressTimeEnd = 0;
    }

    public void setLabels()
    {
        hpLabelA.setText("HP: " + playerManager.getTankA().getHp());
        hpLabelB.setText("HP: " + playerManager.getTankB().getHp());
        activeTankLabel.setText(playerManager.getActiveTank().getPlayerName());
        timerLabel.setText(playerManager.getTimerSeconds());
    }
}
