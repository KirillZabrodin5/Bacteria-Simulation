package model.entity.commands;

import model.WorldContext;
import model.Direction;
import model.entity.Bacteria;

public class ChangeDirectionCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        bacteria.setIndexCommand(bacteria.getIndexCommand() + 1);
    }

    @Override
    public boolean isFinalCommand() {
        return false;
    }
}
