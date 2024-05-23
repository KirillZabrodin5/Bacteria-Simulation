package model.entity.commands;

import model.Direction;
import model.ModelContext;
import model.entity.*;

public class MoveCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, ModelContext modelContext) {
        Direction step = modelContext.generateValidStep(bacteria);
        int newX = bacteria.getCoords().x + step.getX();
        int newY = bacteria.getCoords().y + step.getY();
        int healthPoints = bacteria.getHp();
        if (healthPoints == 0) {
            modelContext.killCell(bacteria);
            return;
        }
        bacteria.setHealthPoints(healthPoints - 1);
        AbstractEntity encounteredEntity = modelContext.checkCellForAnEntity(bacteria, step, Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity == null) {
            modelContext.moveBacteria(bacteria,  newX, newY);
        } else {
            if (encounteredEntity instanceof Food food) {
                modelContext.eatFood(food);
                modelContext.moveBacteria(bacteria, newX, newY);
                bacteria.setHealthPoints(healthPoints + 10);
            } else if (encounteredEntity instanceof Poison) {
                modelContext.eatPoison(bacteria);
            }
        }
    }
}
