package model.entity;

import model.WorldContext;
import model.entity.commands.*;

import java.awt.*;
import java.util.Random;

public class Bacteria extends AbstractEntity {
    private final int brainSize = 64;
    private final int[] brain = new int[brainSize];
    private final int maxCountCommand = 10;
    private int indexCommand = 0; //тут должны быть чиселки от 0 до 63
    private int healthPoints = 15;
    public Bacteria(Point coords) {
        super(coords);
        fillBrain();
    }

    @Override
    public void update(WorldContext modelContext) {
        //тут надо добавлять еще то, что какие-то команды являются завершающими, какие-то нет и стоит до 10 раз выполнять,
        //чтобы сделать шаг
        int countCommand = 0;

        if (healthPoints == 0) {
            modelContext.killCell(this);
            return;
        }

        while (countCommand != maxCountCommand) {
            int commandCode = brain[indexCommand];
            if (commandCode > 0) {
                if (commandCode < 8) {
                    new MoveCommand().execute(this, commandCode, modelContext);
                    break;
                } else if (commandCode < 16) {
                    new CatchCommand().execute(this, commandCode, modelContext);
                    break;
                } else if (commandCode < 24) {
                    new LookCommand().execute(this, commandCode, modelContext);
                } else if (commandCode < 32) {
                    new ChangeDirectionCommand().execute(this, commandCode, modelContext);
                } else if (commandCode < 64) {
                    new JumpCommand().execute(this, commandCode, modelContext);
                }
            }
            countCommand++;
            indexCommand = (indexCommand + 1) % brainSize;
        }
    }

    public void fillBrain() {
        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            brain[i] = random.nextInt(64);
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
