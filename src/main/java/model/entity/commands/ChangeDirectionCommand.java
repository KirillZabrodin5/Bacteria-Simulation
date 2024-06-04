package model.entity.commands;

import model.WorldContext;
import model.entity.Bacteria;

public class ChangeDirectionCommand extends BaseCommand {
    private static final int healphChangeDirection = 2;
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        bacteria.setIndexCommand(bacteria.getIndexCommand() + 1);
        bacteria.setHealthPoints(bacteria.getHealthPoints() - healphChangeDirection);
    }

    @Override
    public boolean isFinalCommand() {
        return false;
    }
}
