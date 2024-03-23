package model.entity;

import model.ApplicationMath;

public class Robot implements Locatable{
    private volatile double robotPositionX = 300;

    private volatile double robotPositionY = 300;

    private volatile double robotDirection = 0;

    private final double maxVelocity = 0.1;

    private final double maxAngularVelocity = 0.001;

    public void moveRobot(double velocity, double angularVelocity, double duration)
    {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);
        angularVelocity = ApplicationMath.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = robotPositionX + velocity / angularVelocity *
                (Math.sin(robotDirection  + angularVelocity * duration) -
                        Math.sin(robotDirection));
        if (!Double.isFinite(newX))
        {
            newX = robotPositionX + velocity * duration * Math.cos(robotDirection);
        }
        double newY = robotPositionY - velocity / angularVelocity *
                (Math.cos(robotDirection  + angularVelocity * duration) -
                        Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = robotPositionY + velocity * duration * Math.sin(robotDirection);
        }
        robotPositionX = newX;
        robotPositionY = newY;
        double newDirection = ApplicationMath.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    public double getRobotPositionX() {
        return robotPositionX;
    }

    public double getRobotPositionY() {
        return robotPositionY;
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
