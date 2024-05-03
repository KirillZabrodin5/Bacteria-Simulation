package model.entity;

public abstract class AbstractEntity {
    public int xRobot;
    public int yRobot;
    public int xTarget;
    public int yTarget;
    public int robotDirection;
    public int sizeRobot;

    public AbstractEntity(int xRobot, int yRobot, int xTarget, int yTarget, int sizeRobot) {
        this.xRobot = xRobot;
        this.yRobot = yRobot;
        this.xTarget = xTarget;
        this.yTarget = yTarget;
        this.sizeRobot = sizeRobot;
    }
    public abstract void update();
}
