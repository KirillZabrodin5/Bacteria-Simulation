package model.entity.commands;

import model.WorldContext;
import model.entity.Bacteria;

public class JumpCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        bacteria.setIndexCommand(bacteria.getIndexCommand() + commandCode);
    }

    @Override
    public boolean isFinalCommand() {
        return false;
    }
}
