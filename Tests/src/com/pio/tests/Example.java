package com.pio.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.BackgroundActors.Cloud;
import com.pio.tanks.BackgroundActors.Tree;
import com.pio.tanks.BackgroundManager;
import com.pio.tanks.Tank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(GdxTestRunner.class)
public class Example
{
    @Test
    public void test()
    {
        assertTrue("Nie działa", Gdx.files.local("..\\core\\assets\\tree02.png").exists());
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
        Tree t = new Tree(100,100);
        float posX = t.getX();
        float getY = t.getY();
        assertTrue(true);
    }

    @Test
    public void cloudTest()
    {
        Cloud c = new Cloud(100,100);
        assertTrue(true);
    }
}
