package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class BackgroundManager
{

    private Background background;
    private final int CLOUDS_NUMBER = 15;
    private final int TREES_NUMBER = 15;
    ArrayList<Cloud> clouds;
    ArrayList<Tree> trees;

    public BackgroundManager(Stage stage)
    {
        clouds = new ArrayList<>();
        trees = new ArrayList<>();

        for (int i = 0; i < CLOUDS_NUMBER; i++)
            clouds.add(new Cloud(stage, -500, 1800));

        for(float posX = -500; posX < 1800; posX += 2300/TREES_NUMBER)
            trees.add(new Tree(stage, (int)(posX - 30), (int)(posX + 30)));

        background = new Background(stage);
        background.setPosition(-500, -300);
        background.toBack();
    }

}
