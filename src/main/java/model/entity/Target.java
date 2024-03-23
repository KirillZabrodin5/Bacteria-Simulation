package model.entity;

import java.awt.*;
public class Target implements Locatable {
    private volatile int targetPositionX = 100;

    private volatile int targetPositionY = 100;

    public void setTargetPosition(Point p)
    {
        targetPositionX = p.x;
        targetPositionY = p.y;
    }

    public int getTargetPositionX() {
        return targetPositionX;
    }

    public int getTargetPositionY() {
        return targetPositionY;
    }
}
