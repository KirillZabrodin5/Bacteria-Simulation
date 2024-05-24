package model.entity.commands;

import model.Step;
import model.WorldContext;
import model.entity.*;

public class LookCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, int commandCode, WorldContext worldContext) {
        Step step = worldContext.generateValidStep(bacteria);
        AbstractEntity entity = worldContext.checkCellForAnEntity(bacteria, step, Bacteria.class,
                Food.class, Poison.class, Wall.class);
        if (entity instanceof Bacteria) {
            //todo
        } else if (entity instanceof Food) {
            //todo
        } else if (entity instanceof Poison) {
            //todo
        } else if (entity instanceof Wall) {
            //todo
        }
    }
}
