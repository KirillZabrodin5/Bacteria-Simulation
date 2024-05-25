package model.entity.commands;

import model.entity.*;

public enum EntityToValue {
    POISON(1),
    WALL(2),
    BACTERIA(3),
    FOOD(4),
    EMPTY(5);
    private int value;
    EntityToValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
