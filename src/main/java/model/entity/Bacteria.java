package model.entity;

import model.ModelContext;
import model.Steps;

import java.awt.*;

public class Bacteria extends AbstractEntity {
    private int hp = 15;
    public Bacteria(Point coords) {
        super(coords);
    }

    public void moveBacteria(ModelContext modelContext) {
        Steps step = modelContext.generateValidStep(this);
        int newX = getCoords().x + step.getX();
        int newY = getCoords().y + step.getY();
        AbstractEntity encounteredEntity = modelContext.move(this, step, Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity == null) {
            modelContext.moveBacteria(this,  newX, newY);
        } else {
            if (encounteredEntity instanceof Wall || encounteredEntity instanceof Bacteria) {
                //надо ли что-нибудь в этом if делать?
            } else if (encounteredEntity instanceof Food) {
                modelContext.eatFood((Food) encounteredEntity);
                modelContext.moveBacteria(this, newX, newY);
                hp+=10;
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
        return hp;
    }
}
