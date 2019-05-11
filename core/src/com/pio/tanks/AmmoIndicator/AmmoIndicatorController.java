package com.pio.tanks.AmmoIndicator;

import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class AmmoIndicatorController
{
    private AmmoContainer[] containerA;
    private AmmoContainer[] containerB;

    public AmmoIndicatorController(Table table)
    {
        containerA = new AmmoContainer[]{new AmmoContainer(table, 0, 5), new AmmoContainer(table, 1, 5)};
        containerB = new AmmoContainer[]{new AmmoContainer(table, 0, 5), new AmmoContainer(table, 1, 5)};
        containerA[0].setOnTable();
        table.add();
        containerB[0].setOnTable();
        table.row();
        containerA[1].setOnTable();
        table.add();
        containerB[1].setOnTable();
        table.top();
    }

    public void removeShell(char player, int shellType)
    {
        if (player == 'A')
            containerA[shellType].destroyOneShell();
        if (player == 'B')
            containerB[shellType].destroyOneShell();
    }
}
