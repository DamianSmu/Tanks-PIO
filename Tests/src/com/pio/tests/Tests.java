package com.pio.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.BackgroundActors.Cloud;
import com.pio.tanks.BackgroundActors.Tower;
import com.pio.tanks.BackgroundActors.Tree;
import com.pio.tanks.BackgroundManager;
import com.pio.tanks.Tank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(GdxTestRunner.class)
public class Tests
{
    @Test
    public void treePictureTest()
    {
        assertTrue("Nie działa", Gdx.files.local("..\\core\\assets\\tree02.png").exists());
    }

    @Test
    public void cloudPictureTest()
    {
        assertTrue("Nie działa", Gdx.files.local("..\\core\\assets\\cloud1.png").exists());
    }

    @Test
    public void bulletPictureTest()
    {
        assertTrue("Nie działa", Gdx.files.local("..\\core\\assets\\tank_bullet3.png").exists());
    }

    @Test
    public void tankTest()
    {
        Tank t = new Tank(0,0);
        assertTrue(t.isAbleToShoot());
    }

    @Test
    public void treeTest()
    {
        Tree t = new Tree(100,200);
        float posX = t.getX();
        assertEquals(150, posX, 50);

    }

    @Test
    public void cloudTest()
    {
        Cloud c = new Cloud(100,200);
        float posX = c.getX();
        assertEquals(150, posX, 50);
    }

    @Test
    public void towerTest(){
        Tower t =  new Tower(100,500);
        float width = t.getWidth();
        float height = t.getHeight();

        assertEquals(100, width, 0);
        assertEquals(500, height, 0);
    }
}
