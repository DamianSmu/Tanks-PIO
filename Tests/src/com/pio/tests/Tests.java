package com.pio.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pio.tanks.AbstractShell;
import com.pio.tanks.BackgroundActors.Cloud;
import com.pio.tanks.BackgroundActors.Tower;
import com.pio.tanks.BackgroundActors.Tree;
import com.pio.tanks.StandardShell;
import com.pio.tanks.Tank;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(GdxTestRunner.class)
public class Tests
{
    private Stage stage;

    @Before
    public void initStage()
    {
        stage = new Stage(new FitViewport(1000, 800), Mockito.mock(SpriteBatch.class));
    }


    @Test
    public void treePictureTest()
    {
        assertTrue("Texture \"treeXX.png\" not found", Gdx.files.internal("tree02.png").exists());
    }

    @Test
    public void cloudPictureTest()
    {
        assertTrue("Texture \"cloudXX.png\" not found", Gdx.files.internal("cloud1.png").exists());
    }

    @Test
    public void bulletPictureTest()
    {
        assertTrue("Texture \"tank_bulletXX.png\" not found", Gdx.files.internal("tank_bullet3.png").exists());
    }

    @Test
    public void tankTest()
    {
        Tank t = new Tank(0, 0, stage, false);
        assertTrue(t.isAbleToShoot());
    }

    @Test
    public void treeTest()
    {
        Tree t = new Tree(stage,100,200);
        float posX = t.getX();
        assertEquals(150, posX, 50);

    }

    @Test
    public void cloudTest()
    {
        Cloud c = new Cloud(stage, 100,200);
        float posX = c.getX();
        assertEquals(150, posX, 50);
    }

    @Test
    public void towerTest(){
        Tower t =  new Tower(stage);
        float width = t.getWidth();
        float height = t.getHeight();

        assertEquals(66, width, 0);
        assertEquals(227, height, 0);
    }

    @Test
    public void detectHitTest(){
        StandardShell shell = new StandardShell(stage, 50, 60,0,0);
        Tank tank = new Tank(50, 50, stage, false);
        shell.act((1/60f));
        assertEquals(80, tank.getHp());
    }

    @Test
    public void powerPictureTest()
    {
        assertTrue("Texture \"power.png\" not found", Gdx.files.internal("power.png").exists());
    }
}
