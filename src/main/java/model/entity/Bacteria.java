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
    //private HandlerCommand handlerCommand = new HandlerCommand();
    private int healthPoints = 50;
    private volatile Direction direction; //по сути оно нужно только для того, чтобы глаза рисовать в нужном направлении... или нет

    private static final MoveCommand moveCommand = new MoveCommand();
    private static final CatchCommand catchCommand = new CatchCommand();
    private static final LookCommand lookCommand = new LookCommand();
    private static final ChangeDirectionCommand changeDirectionCommand = new ChangeDirectionCommand();
    private static final JumpCommand jumpCommand = new JumpCommand();

    public Bacteria(Point coords) {
        super(coords);
        fillBrain();
        direction = (Direction.values())[brain[indexCommand] % 8];
    }

    @Override
    public void update(WorldContext modelContext) {
        int countExecutedCommand = 0;
        boolean isStop = false;

        while (countExecutedCommand < maxCountCommand && !isStop) {
            int commandCode = brain[indexCommand];
            BaseCommand command = getCommand(commandCode);
            setDirection((Direction.values())[commandCode % 8]);

            if (command != null) {
                command.execute(this, commandCode, modelContext);
                isStop = command.isFinalCommand();
            }

            countExecutedCommand++;

            if (healthPoints <= 0) {
                modelContext.killCell(this);
                break;
            }

            if (healthPoints > 50) {
                modelContext.createChild(this);
            }
        }
    }

    private BaseCommand getCommand(int commandCode) {
        if (commandCode < 8) {
            return moveCommand;
        } else if (commandCode < 16) {
            return catchCommand;
        } else if (commandCode < 24) {
            return lookCommand;
        } else if (commandCode < 32) {
            return changeDirectionCommand;
        } else if (commandCode < 64) {
            return jumpCommand;
        }
        return null;
    }

    public void fillBrain() {
        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            brain[i] = random.nextInt(0,64);
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
