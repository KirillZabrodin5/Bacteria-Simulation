package model.entity;

import utils.GameWindowConfig;

import java.awt.*;

public class Food extends AbstractEntity {
    public Food(int x, int y) {
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

    public void setFoodPosition(Point p) {
        x = p.x;
        y = p.y;
    }
}
