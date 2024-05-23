package model.entity;

import model.ModelContext;
import model.Direction;
import model.entity.commands.EatFoodOrNeutralizePoisonCommand;
import model.entity.commands.MoveCommand;

import java.awt.*;
import java.util.Random;

public class Bacteria extends AbstractEntity {
    public int[] brain = new int[64];
    private final int maxCountCommand = 10;
    private int currentCommand = 15; //тут должны быть чиселки от 0 до 63
    private int healthPoints = 15;
    public Bacteria(Point coords) {
        super(coords);
        fillBrain();
    }

    @Override
    public void update(ModelContext modelContext) {

        if (currentCommand > 0) {
            if (currentCommand < 8) {
                new MoveCommand().execute(this, modelContext);
            } else if (currentCommand < 16) {
                new EatFoodOrNeutralizePoisonCommand().execute(this, modelContext);
            } else if (currentCommand < 24) {

            } else if (currentCommand < 32) {

            } else if (currentCommand < 64) {

            }
        }

    }

    public void fillBrain() {
        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            brain[i] = random.nextInt(64);
        }
    }

    public int getHp() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
