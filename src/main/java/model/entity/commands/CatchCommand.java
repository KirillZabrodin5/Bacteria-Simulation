package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.*;

public class CatchCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.WALL);
            return;
        }
        bacteria.setDirection(step);

        int healthPoints = bacteria.getHealthPoints();
        bacteria.setHealthPoints(healthPoints - 1);

        AbstractEntity encounteredEntity = worldContext.checkCellForAnEntity(bacteria, step,
                Bacteria.class, Food.class, Poison.class, Wall.class);

        if (encounteredEntity instanceof Food food) {
            worldContext.eatFood(food);
            bacteria.setHealthPoints(healthPoints + 10);
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.FOOD);
        } else if (encounteredEntity instanceof Poison poison) {
            worldContext.neutralizePoison(poison);
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.POISON);
        } else if (encounteredEntity instanceof Bacteria) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.BACTERIA);
        } else if (encounteredEntity instanceof Wall) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.WALL);
        } else {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.EMPTY);
        }
    }
}
