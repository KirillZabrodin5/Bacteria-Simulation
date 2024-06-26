package model.entity;

import model.WorldContext;

import java.awt.Point;

public abstract class AbstractEntity {
    private Point coords;

    public AbstractEntity(Point coords) {
        this.coords = coords;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public abstract void update(WorldContext worldContext);
}
