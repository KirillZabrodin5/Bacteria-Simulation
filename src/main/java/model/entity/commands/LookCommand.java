package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.*;

public class LookCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = bacteria.getDirection();
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.WALL);
            return;
        }


        AbstractEntity entity = worldContext.getEntityOnCoords(bacteria, step, Bacteria.class,
                Food.class, Poison.class, Wall.class);

        if (entity instanceof Bacteria) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.BACTERIA);
        } else if (entity instanceof Food) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.FOOD);
        } else if (entity instanceof Poison) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.POISON);
        } else if (entity instanceof Wall) {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.WALL);
        } else {
            bacteria.setIndexCommand(bacteria.getIndexCommand() + EntityToValue.EMPTY);
        }
    }

    @Override
    public boolean isFinalCommand() {
        return false;
    }
}
