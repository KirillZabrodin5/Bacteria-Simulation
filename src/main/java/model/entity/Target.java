package model.entity;

import java.awt.*;

public class Target extends AbstractEntity{
    public Target(int x, int y) {
        super(x, y);
    }
    public void setTargetPosition(Point p)
    {
        x = p.x;
        y = p.y;
    }

    @Override
    public void update() {

    }
}
