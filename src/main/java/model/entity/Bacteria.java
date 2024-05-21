package model.entity;

import model.ModelContext;
import model.Direction;

import java.awt.*;

public class Bacteria extends AbstractEntity {
    private int healthPoints = 15;
    public Bacteria(Point coords) {
        super(coords);
    }

    public void moveBacteria(ModelContext modelContext) {
        Direction step = modelContext.generateValidStep(this);
        int newX = getCoords().x + step.getX();
        int newY = getCoords().y + step.getY();
        AbstractEntity encounteredEntity = modelContext.move(this, step, Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity == null) {
            modelContext.moveBacteria(this,  newX, newY);
        } else {
            if (encounteredEntity instanceof Food food) {
                modelContext.eatFood(food);
                modelContext.moveBacteria(this, newX, newY);
                healthPoints+=10;
            } else if (encounteredEntity instanceof Poison) {
                modelContext.eatPoison(this);
            }
        }
    }

    @Override
    public void update(ModelContext modelContext) {
        moveBacteria(modelContext);
    }

    public int getHp() {
        return healthPoints;
    }
}
