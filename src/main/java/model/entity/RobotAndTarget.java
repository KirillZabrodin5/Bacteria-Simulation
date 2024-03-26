package model.entity;

import utils.ApplicationMath;

import java.awt.*;

public class RobotAndTarget extends BaseEntity{
    public RobotAndTarget(double xRobot, double yRobot, double xTarget, double yTarget) {
        super(xRobot, yRobot, xTarget, yTarget);
    }
    private volatile double robotDirection = 0;

    private final double maxVelocity = 0.1;

    private final double maxAngularVelocity = 0.001;

    public void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);
        angularVelocity = ApplicationMath.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = xRobot + velocity / angularVelocity *
                (Math.sin(robotDirection  + angularVelocity * duration) -
                        Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = xRobot + velocity * duration * Math.cos(robotDirection);
        }
        double newY = yRobot - velocity / angularVelocity *
                (Math.cos(robotDirection  + angularVelocity * duration) -
                        Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = yRobot + velocity * duration * Math.sin(robotDirection);
        }
        xRobot = newX;
        yRobot = newY;
        double newDirection = ApplicationMath.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    public void setTargetPosition(Point p)
    {
        xRobot = p.x;
        yRobot = p.y;
    }

    public void update() {
        double distance = ApplicationMath.distance(xTarget, yTarget, xRobot, yRobot);
        if (distance < 0.5)
        {
            return;
        }
        double velocity = maxVelocity;
        double angleToTarget = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
        double angularVelocity = 0;
        if (angleToTarget > robotDirection)
        {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < robotDirection)
        {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, 10);
    }


    /*
    * Переделать методы выше на эти
    * public void update(double duration) {
        double distance = ApplicationMath.distance(target.x, target.y, x, y);
        if (distance < 0.5) {
            return;
        }
        double velocity = maxVelocity;
        double angleToTarget = ApplicationMath.angleTo(x, y, target.x, target.y);
        double angularVelocity = 0;
        if (angleToTarget > direction) {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < direction) {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, duration);
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);
        angularVelocity = ApplicationMath.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);

        x += velocity * duration * Math.cos(direction);
        y += velocity * duration * Math.sin(direction);
        direction = ApplicationMath.asNormalizedRadians(direction + angularVelocity * duration);
    }
    *
    * */
}
