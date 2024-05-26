package model.entity;

import model.Direction;
import model.WorldContext;
import model.entity.commands.*;

import java.awt.*;
import java.util.Random;

public class Bacteria extends AbstractEntity {
    private final int brainSize = 64;
    private final int[] brain = new int[brainSize];
    private final int maxCountCommand = 10;
    private int indexCommand = 0;
    private int healthPoints = 15;
    private Direction direction; //по сути оно нужно только для того, чтобы глаза рисовать в нужном направлении... или нет
    private HandlerCommand handlerCommand = new HandlerCommand();

    public Bacteria(Point coords) {
        super(coords);
        fillBrain();
    }

    @Override
    public void update(WorldContext modelContext) {
        int countExecutedCommand = 0;

        if (healthPoints == 0) {
            modelContext.killCell(this);
            return;
        }

        while (countExecutedCommand < maxCountCommand) {
            int commandCode = brain[indexCommand];

            handlerCommand.execute(this, commandCode, modelContext);

//            if (commandCode < 8) {
//                new MoveCommand().execute(this, commandCode, modelContext);
//                break;
//            } else if (commandCode < 16) {
//                new CatchCommand().execute(this, commandCode, modelContext);
//                break;
//            } else if (commandCode < 24) {
//                new LookCommand().execute(this, commandCode, modelContext);
//            } else if (commandCode < 32) {
//                new ChangeDirectionCommand().execute(this, commandCode, modelContext);
//            } else if (commandCode < 64) {
//                new JumpCommand().execute(this, commandCode, modelContext);
//            }

            countExecutedCommand++;
        }

        if (healthPoints > 50) {
            modelContext.createChild(this);
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

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getIndexCommand() {
        return indexCommand;
    }

    public void setIndexCommand(int indexCommand) {
        this.indexCommand = indexCommand % brainSize;
    }
}
