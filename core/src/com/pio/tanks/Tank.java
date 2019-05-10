package com.pio.tanks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Tank extends Group
{
    public Rectangle rectangle;
    private TextureRegion texture;
    private Turret turret;
    private PowerBar powerBar;
    private Stage stage;
    private int hp;
    private int ammoType1;
    private boolean changedToLeft;
    private String playerName;
    private boolean ableToShoot;
    private AbstractShell firedShell;

    public Tank(float posX, float posY, Stage stage, boolean changedToLeft)
    {
        this.stage = stage;
        hp = 100;
        ammoType1 = 3;
        ableToShoot = true;

        stage.addActor(this);
        texture = new TextureRegion(new Texture("tanks_tankGreen_body1.png"));
        setPosition(posX, posY);
        setWidth(83);
        setHeight(49);

        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());

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

    public void moveBy(int moveDirection)
    {
        if (moveDirection == 0)
            moveBy(-2, 0);
        if (moveDirection == 1)
            moveBy(2, 0);
        rectangle.setPosition(getX(), getY());
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
        Vector2 pos = turret.localToStageCoordinates(new Vector2(turret.getWidth() / 2f, turret.getHeight() / 2f));
        firedShell = new StandardShell(stage, pos.x, pos.y, turret.getRotation(), acceleration / 2f);
        ableToShoot = false;
        ammoType1--;
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
