package model.entity.commands;

import model.WorldContext;
import model.entity.Bacteria;

public interface BaseCommand {
    void execute(Bacteria bacteria, int commandCode, WorldContext worldContext);
}
