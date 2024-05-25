package model.entity.commands;

import model.WorldContext;
import model.Direction;
import model.entity.Bacteria;

public class ChangeDirectionCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];
        if (!worldContext.isValidStep(bacteria, step)) {
            bacteria.setCommandCode(commandCode + EntityToValue.WALL.getValue());
            return;
        }
        bacteria.setDirection(step);
        bacteria.setCommandCode(commandCode + 1);
    }
}
