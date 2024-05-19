package model.entity;

import model.Model;
import model.ModelContext;
import model.Steps;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.Random;

public class Bacteria extends AbstractEntity {
    public Bacteria(Point coords) {
        super(coords);
    }

    public void moveBacteria(ModelContext modelContext) {
        Steps step = modelContext.generateValidStep(this);
        int oldX = getCoords().x;
        int oldY = getCoords().y;
        int newX = getCoords().x + step.getX();
        int newY = getCoords().y + step.getY();
        AbstractEntity encounteredEntity = modelContext.move(this, step, Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity == null) {
            modelContext.moveBacteria(this, oldX, oldY, newX, newY);
        } else {
            if (encounteredEntity instanceof Wall || encounteredEntity instanceof Bacteria) {
                //надо ли что-нибудь в этом if делать?
            } else if (encounteredEntity instanceof Food) {
                modelContext.moveBacteria(this, oldX, oldY, newX, newY);
                modelContext.eatFood((Food) encounteredEntity);
            } else if (encounteredEntity instanceof Poison) {
                modelContext.eatPoison(this);
            }
        }
    }

    @Override
    public void update(ModelContext modelContext) {
        moveBacteria(modelContext);
    }
}
