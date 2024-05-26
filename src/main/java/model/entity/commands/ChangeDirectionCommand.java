package model.entity.commands;

import model.WorldContext;
import model.Direction;
import model.entity.Bacteria;

public class ChangeDirectionCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Direction step = (Direction.values())[commandCode % 8];

        bacteria.setDirection(step);

        bacteria.setIndexCommand(bacteria.getIndexCommand() + 1);
    }
}
