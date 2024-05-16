package model.entity;

import java.awt.*;

public class Food extends AbstractEntity {
    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    public void setFoodPosition(Point p) {
        x = p.x;
        y = p.y;
    }
}
