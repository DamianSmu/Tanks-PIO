package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pio.tanks.BackgroundActors.Background;
import com.pio.tanks.BackgroundActors.Cloud;
import com.pio.tanks.BackgroundActors.Tower;
import com.pio.tanks.BackgroundActors.Tree;

import java.util.ArrayList;

public class BackgroundManager
{

    private final int CLOUDS_NUMBER = 15;
    private final int TREES_NUMBER = 15;
    private Background background;
    private Tower tower;
    private ArrayList<Cloud> clouds;
    private ArrayList<Tree> trees;

    public BackgroundManager(Stage stage)
    {
        clouds = new ArrayList<>();
        trees = new ArrayList<>();
        tower = new Tower(stage);

        for (int i = 0; i < CLOUDS_NUMBER; i++)
            clouds.add(new Cloud(stage, -500, 1800));

        for (float posX = -500; posX < 1800; posX += 2300 / (TREES_NUMBER - 1))
            trees.add(new Tree(stage, (int) (posX - 30), (int) (posX + 30)));

        background = new Background(stage);
        background.setPosition(-800, -300);
        background.toBack();
    }

    public Background getBackground()
    {
        return background;
    }

    public Tower getTower()
    {
        return tower;
    }

    public ArrayList<Cloud> getClouds()
    {
        return clouds;
    }

    public ArrayList<Tree> getTrees()
    {
        return trees;
    }
}
