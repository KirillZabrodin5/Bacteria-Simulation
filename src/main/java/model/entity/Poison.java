package model.entity;

import model.ModelContext;

import java.awt.Point;

public class Poison extends AbstractEntity {
    public Poison(Point coords) {
        super(coords);
    }

    @Override
    public void update(ModelContext modelContext) {}
}
