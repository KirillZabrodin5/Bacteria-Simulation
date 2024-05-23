package model.entity.commands;

import model.ModelContext;
import model.entity.Bacteria;
import model.entity.Food;
import model.entity.Poison;
import model.entity.Wall;

public class LookCommand implements BaseCommand {
    @Override
    public void execute(Bacteria bacteria, ModelContext modelContext) {
        //modelContext.checkCellForAnEntity(bacteria, step, Bacteria.class, Food.class, Poison.class, Wall.class);
    }
}
