package model.entity.commands;

import model.WorldContext;
import model.entity.Bacteria;

public abstract class BaseCommand {
    protected static final int healphStep = 1; //вычитаем 1, когда делаем шаг
    protected static final int healphEatFood = 10; //прибавляем 10, когда едим еду
    public abstract void execute(Bacteria bacteria, int commandCode, WorldContext worldContext);
    public abstract boolean isFinalCommand();
}
