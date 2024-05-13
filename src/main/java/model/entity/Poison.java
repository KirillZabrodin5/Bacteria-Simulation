package model.entity;

import utils.GameWindowConfig;

import java.awt.*;

public class Poison extends AbstractEntity {
    public Poison(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    public void createNewInstance(){
        GameWindowConfig infoGameWindow = new GameWindowConfig();
        x = (int) (Math.random() * infoGameWindow.getCountOfCells());
        y = (int) (Math.random() * infoGameWindow.getCountOfCells());
    }

    public void setPoisonPosition(Point p) {
        x = p.x;
        y = p.y;
    }
}
