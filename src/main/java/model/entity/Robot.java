package model.entity;

import utils.ApplicationMath;

public class Robot extends BaseEntity{
    public Robot(double x, double y) {
        super(x, y);
    }
    private volatile double robotDirection = 0;

    private final double maxVelocity = 0.1;

    private final double maxAngularVelocity = 0.001;

    public void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);
        angularVelocity = ApplicationMath.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = x + velocity / angularVelocity *
                (Math.sin(robotDirection  + angularVelocity * duration) -
                        Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = x + velocity * duration * Math.cos(robotDirection);
        }
        double newY = y - velocity / angularVelocity *
                (Math.cos(robotDirection  + angularVelocity * duration) -
                        Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = y + velocity * duration * Math.sin(robotDirection);
        }
        x = newX;
        y = newY;
        double newDirection = ApplicationMath.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public double getMaxAngularVelocity() {
        return maxAngularVelocity;
    }
}
