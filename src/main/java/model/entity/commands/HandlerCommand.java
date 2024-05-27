package model.entity.commands;

import model.WorldContext;
import model.entity.Bacteria;

public class HandlerCommand {
    private final MoveCommand moveCommand = new MoveCommand();
    private final CatchCommand catchCommand = new CatchCommand();
    private final LookCommand lookCommand = new LookCommand();
    private final ChangeDirectionCommand changeDirectionCommand = new ChangeDirectionCommand();
    private final JumpCommand jumpCommand = new JumpCommand();

    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        switch (commandCode) {
            case 0,1,2,3,4,5,6,7:
                moveCommand.execute(bacteria, commandCode, worldContext);
                break;
            case 8,9,10,11,12,13,14,15:
                catchCommand.execute(bacteria, commandCode, worldContext);
                break;
            case 16,17,18,19,20,21,22,23:
                lookCommand.execute(bacteria, commandCode, worldContext);
                break;
            case 24,25,26,27,28,29,30,31:
                changeDirectionCommand.execute(bacteria, commandCode, worldContext);
                break;
            default:
                jumpCommand.execute(bacteria, commandCode, worldContext);
                break;
        }
    }
}
