package model.entity;

import model.WorldContext;

import java.awt.Point;

public class Poison extends AbstractEntity {
    public Poison(Point coords) {
        super(coords);
    }

    @Override
    public void update(WorldContext modelContext) {}
}
