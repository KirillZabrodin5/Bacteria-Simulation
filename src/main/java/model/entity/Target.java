package model.entity;

import java.awt.*;
public class Target extends BaseEntity {
    public Target(double x, double y) {
        super(x, y);
    }
    public void setTargetPosition(Point p)
    {
        x = p.x;
        y = p.y;
    }
}
