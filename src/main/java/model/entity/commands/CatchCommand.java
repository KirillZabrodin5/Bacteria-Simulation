package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.*;

public class CatchCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];
        int newX = bacteria.getCoords().x + step.getX();
        int newY = bacteria.getCoords().y + step.getY();
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
            return;
        }
        bacteria.setDirection(step);
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
            bacteria.setCommandCode(commandCode + EntityToValue.FOOD.getValue());
        } else if (encounteredEntity instanceof Poison poison) {
            worldContext.neutralizePoison(poison);
            bacteria.setCommandCode(commandCode + EntityToValue.POISON.getValue());
        } else if (encounteredEntity instanceof Bacteria) {
            bacteria.setCommandCode(commandCode + EntityToValue.BACTERIA.getValue());
        } else if (encounteredEntity instanceof Wall) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
        } else {
            bacteria.setCommandCode(commandCode + EntityToValue.EMPTY.getValue());
        }
    }
}
