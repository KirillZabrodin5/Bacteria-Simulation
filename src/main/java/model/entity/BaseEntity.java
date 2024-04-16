package model.entity;

public abstract class BaseEntity {
    public volatile double xRobot;
    public volatile double yRobot;
    public volatile double xTarget;
    public volatile double yTarget;
    public volatile double robotDirection;

    public BaseEntity(double xRobot, double yRobot, double xTarget, double yTarget) {
        this.xRobot = xRobot;
        this.yRobot = yRobot;
        this.xTarget = xTarget;
        this.yTarget = yTarget;
    }
    public abstract void update();
}
