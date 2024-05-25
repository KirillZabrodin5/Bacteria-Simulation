package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.*;

public class LookCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
            return;
        }
        bacteria.setDirection(step);
        AbstractEntity entity = worldContext.checkCellForAnEntity(bacteria, step, Bacteria.class,
                Food.class, Poison.class, Wall.class);

        if (entity instanceof Bacteria) {
            bacteria.setCommandCode(commandCode + EntityToValue.BACTERIA.getValue());
        } else if (entity instanceof Food) {
            bacteria.setCommandCode(commandCode + EntityToValue.FOOD.getValue());
        } else if (entity instanceof Poison) {
            bacteria.setCommandCode(commandCode + EntityToValue.POISON.getValue());
        } else if (entity instanceof Wall) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
        } else {
            bacteria.setCommandCode(commandCode + EntityToValue.EMPTY.getValue());
        }
    }
}
