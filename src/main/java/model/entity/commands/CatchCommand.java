package model.entity.commands;

import model.Step;
import model.WorldContext;
import model.entity.*;

public class CatchCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Step step = worldContext.generateValidStep(bacteria);
        int newX = bacteria.getCoords().x + step.getX();
        int newY = bacteria.getCoords().y + step.getY();
        int healthPoints = bacteria.getHealthPoints();
        if (healthPoints == 0) {
            worldContext.killCell(bacteria);
            return;
        }
        bacteria.setHealthPoints(healthPoints - 1);
        AbstractEntity encounteredEntity = worldContext.checkCellForAnEntity(bacteria, step, Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity instanceof Food food) {
            worldContext.eatFood(food);
            worldContext.moveBacteria(bacteria, newX, newY);
            bacteria.setHealthPoints(healthPoints + 10);
        } else if (encounteredEntity instanceof Poison poison) {
            worldContext.neutralizePoison(poison);
        }
    }
}
