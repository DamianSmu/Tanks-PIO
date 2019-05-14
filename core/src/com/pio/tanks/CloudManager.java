package com.pio.tanks;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Random;

public class CloudManager {

    ArrayList<Cloud> clouds;
    private final int NUMBER = 15;
    private Random random;
    private Stage stage;

    public CloudManager(Stage stage) {
        random = new Random();
        clouds = new ArrayList<>();
        for(int i = 0; i < NUMBER; i++) {
            Cloud cloud = new Cloud(stage, -500, 1000);

            clouds.add(cloud);
        }

        this.stage = stage;
    }

    public void act() {
        for(Cloud c: clouds) {
            if(c.getX() < -600)
                c.setX(2300);
            else if(c.getX() > 2300)
                c.setX(-600);
        }
    }
}
