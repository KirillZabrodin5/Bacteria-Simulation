package model.entity;

import model.InfoGameWindow;

import java.awt.*;

public class Poison extends AbstractEntity {
    public Poison(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    public void createNewInstance(){
        InfoGameWindow infoGameWindow = new InfoGameWindow();
        x = (int) (Math.random() * infoGameWindow.getCountOfCells());
        y = (int) (Math.random() * infoGameWindow.getCountOfCells());
    }

    public void setPoisonPosition(Point p) {
        x = p.x;
        y = p.y;
    }
}
