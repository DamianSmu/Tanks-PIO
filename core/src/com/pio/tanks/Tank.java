package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.Animations.ShotAnimation;
import com.pio.tanks.Events.ShotEvent;

public class Tank extends Group
{
    private Polygon boundaryPolygon;
    private TextureRegion texture;
    private Turret turret;
    private PowerBar powerBar;
    private Stage stage;
    private int hp;
    private int ammoType0;
    private int ammoType1;
    private boolean changedToLeft;
    private String playerName;
    private boolean ableToShoot;
    private AbstractShell firedShell;

    public Tank(float posX, float posY, Stage stage, boolean changedToLeft)
    {
        this.stage = stage;
        hp = 100;
        ammoType0 = 5;
        ammoType1 = 5;
        ableToShoot = true;

        stage.addActor(this);
        texture = new TextureRegion(Assets.TANK_TEX);
        setPosition(posX, posY);
        setWidth(83);
        setHeight(49);

        turret = new Turret(stage);
        addActor(turret);
        turret.setPosition(42, 37);
        turret.setOrigin(0, turret.getHeight() / 2f);
        turret.toFront();

        if (!changedToLeft)
            changeToLeft();

        powerBar = new PowerBar(stage);
        addActor(powerBar);
        powerBar.setPosition(-9, -20);
        getDebug();

        setBoundaryPolygon(8);
    }

    private void changeToLeft()
    {
        texture.flip(true, false);
        turret.setRotation(180);
        changedToLeft = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (isVisible())
        {
            batch.draw(texture,
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    public void setBoundaryPolygon(int numSides)
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = new float[2 * numSides];
        for (int i = 0; i < numSides; i++)
        {
            float angle = i * 6.28f / numSides;

            vertices[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;

            vertices[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public void moveBy(int moveDirection)
    {
        if (moveDirection == 0)
            moveBy(-2, 0);
        if (moveDirection == 1)
            moveBy(2, 0);
    }

    public void rotateTurret(int rotateDirection)
    {
        if (changedToLeft)
            turret.setRotation(MathUtils.clamp(turret.getRotation(), 90, 180));
        else
            turret.setRotation(MathUtils.clamp(turret.getRotation(), 0, 90));

        if (rotateDirection == 0)
            turret.rotateBy(2);
        if (rotateDirection == 1)
            turret.rotateBy(-2);
    }

    public void shot(float acceleration/* BulletType */)
    {
        Vector2 pos = new Vector2(turret.getWidth() * MathUtils.cosDeg(turret.getRotation()), (turret.getWidth() * MathUtils.sinDeg(turret.getRotation()))).add(turret.localToStageCoordinates(new Vector2(0,0)));
        if(changedToLeft)
            pos.y -= turret.getHeight()/2f;
        else
            pos.y += turret.getHeight()/2f;
        firedShell = new StandardShell(stage, pos.x , pos.y, turret.getRotation(), acceleration / 2f);
        ableToShoot = false;
        ammoType1--;
        fire(new ShotEvent(0, firedShell));

        new ShotAnimation(stage, pos.x, pos.y);
    }

    public PowerBar getPowerBar()
    {
        return powerBar;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void decreaseHp (int value)
    {
        hp -= value;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public void ableToShoot()
    {
        ableToShoot = true;
    }

    public boolean isAbleToShoot()
    {
        return ableToShoot;
    }

    public AbstractShell getFiredShell()
    {
        return firedShell;
    }

}
