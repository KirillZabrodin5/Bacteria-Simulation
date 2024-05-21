package model.entity;

import model.ModelContext;

import java.awt.Point;

public class Food extends AbstractEntity {
    public Food(Point coords) {
        super(coords);
    }

    @Override
    public void update(ModelContext modelContext) {}
}
