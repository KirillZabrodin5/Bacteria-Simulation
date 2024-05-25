package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.*;

public class MoveCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];
        int newX = bacteria.getCoords().x + step.getX();
        int newY = bacteria.getCoords().y + step.getY();
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
            return;
        }
        int healthPoints = bacteria.getHealthPoints();
        bacteria.setHealthPoints(healthPoints - 1);
        bacteria.setDirection(step);
        AbstractEntity encounteredEntity = worldContext.checkCellForAnEntity(bacteria, step,
                Bacteria.class, Food.class, Poison.class, Wall.class);
        if (encounteredEntity == null) {
            worldContext.moveBacteria(bacteria,  newX, newY);
            bacteria.setCommandCode(commandCode + EntityToValue.EMPTY.getValue());
        } else {
            if (encounteredEntity instanceof Food food) {
                bacteria.setCommandCode(commandCode + EntityToValue.FOOD.getValue());
                worldContext.eatFood(food);
                worldContext.moveBacteria(bacteria, newX, newY);
                bacteria.setHealthPoints(healthPoints + 10);
            } else if (encounteredEntity instanceof Poison) {
                bacteria.setCommandCode(commandCode + EntityToValue.POISON.getValue());
                worldContext.eatPoison(bacteria);
            } else if (encounteredEntity instanceof Bacteria) {
                bacteria.setCommandCode(commandCode + EntityToValue.BACTERIA.getValue());
            } else if (encounteredEntity instanceof Wall) {
                bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
            }
        }
    }
}
