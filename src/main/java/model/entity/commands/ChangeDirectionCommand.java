package model.entity.commands;

import model.WorldContext;
import model.Step;
import model.entity.Bacteria;

public class ChangeDirectionCommand implements BaseCommand {
    private final static int DIFFERENCE_BETWEEN_COMMAND_NUMBER_AND_STEP = 24;

    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        //примерно так это должно выглядеть
        Step step = (Step.values())[commandCode - DIFFERENCE_BETWEEN_COMMAND_NUMBER_AND_STEP];
        //modelContext.changeDirection();


        //то, куда робот сходил в последний раз - это его направление (например, он ходил вверх - значит его
        //направление - это вверх)

        //как узнать, куда робот ходил последний раз? - мб, создать какую-то мапу или enum с градусами и шагом

    }
}
