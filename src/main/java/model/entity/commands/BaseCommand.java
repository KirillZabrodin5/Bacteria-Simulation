package model.entity.commands;

import model.ModelContext;
import model.entity.Bacteria;

public interface BaseCommand {
    void execute(Bacteria bacteria, ModelContext modelContext);
}
