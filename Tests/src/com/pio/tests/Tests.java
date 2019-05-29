package com.pio.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pio.tanks.*;
import com.pio.tanks.BackgroundActors.Cloud;
import com.pio.tanks.BackgroundActors.Tower;
import com.pio.tanks.BackgroundActors.Tree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;


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
    public void backgroundManagerTest()
    {
        BackgroundManager bm = new BackgroundManager(stage);
        assertEquals(bm.getClouds().size(), 15);
        assertEquals(bm.getTrees().size(), 15);
        assertNotNull(bm.getBackground());
        assertNotNull(bm.getTower());
    }

    @Test
    public void detectHitTest(){
        StandardShell shell = new StandardShell(stage, 50, 60,0,0);
        Tank tank = new Tank(50, 50, stage, false);
        shell.act((1/60f));
        assertEquals(80, tank.getHp());
    }

    @Test
    public void getFiredShotTest()
    {
        Tank t = new Tank(0, 0, stage, false);
        assertNull(t.getFiredShell());
        t.shot(100f);
        assertNotNull(t.getFiredShell());
    }

    @Test
    public void playerManagerTest()
    {
        PlayerManager pm = new PlayerManager(stage, new CameraActor());
        assertEquals(pm.getActiveTank(), pm.getTankA());
        pm.nextTurn();
        assertEquals(pm.getActiveTank(), pm.getTankB());
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
    public void treeTexturesLoadTest()
    {
        assertTrue("Texture \"tree02.png\" not found", Gdx.files.internal("tree02.png").exists());
        assertTrue("Texture \"tree10.png\" not found", Gdx.files.internal("tree10.png").exists());
        assertTrue("Texture \"tree13.png\" not found", Gdx.files.internal("tree13.png").exists());
        assertTrue("Texture \"tree20.png\" not found", Gdx.files.internal("tree20.png").exists());
    }

    @Test
    public void cloudTexturesLoadTest()
    {
        assertTrue("Texture \"cloud1.png\" not found", Gdx.files.internal("cloud1.png").exists());
        assertTrue("Texture \"cloud4.png\" not found", Gdx.files.internal("cloud4.png").exists());
        assertTrue("Texture \"cloud5.png\" not found", Gdx.files.internal("cloud5.png").exists());
        assertTrue("Texture \"cloud6.png\" not found", Gdx.files.internal("cloud6.png").exists());
    }

    @Test
    public void bulletTexturesLoadTest()
    {
        assertTrue("Texture \"tank_bullet4.png\" not found", Gdx.files.internal("tank_bullet3.png").exists());
        assertTrue("Texture \"tank_bullet4.png\" not found", Gdx.files.internal("tank_bullet4.png").exists());
    }

    @Test
    public void tankAbilityToShotTest()
    {
        Tank t = new Tank(0, 0, stage, false);
        assertTrue(t.isAbleToShoot());
        t.shot(100f);
        assertFalse(t.isAbleToShoot());
    }

    @Test
    public void treeTest()
    {
        Tree t = new Tree(stage,100,200);
        float posX = t.getX();
        assertEquals(150, posX, 50);
    }

    @Test
    public void powerPictureTest()
    {
        assertTrue("Texture \"power.png\" not found", Gdx.files.internal("power.png").exists());
    }

    @Test
    public void tankHpTest()
    {
        Tank tk = new Tank(0, 0, stage, false);
        assertEquals(100, tk.getHp());
    }
}
