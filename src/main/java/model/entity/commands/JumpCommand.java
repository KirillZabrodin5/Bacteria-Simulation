package model.entity.commands;

import model.Direction;
import model.WorldContext;
import model.entity.Bacteria;

public class JumpCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        bacteria.setCommandCode(commandCode*2);
    }
}
