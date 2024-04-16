package model.entity;

import utils.ApplicationMath;

import java.awt.*;

public class RobotAndTarget extends BaseEntity{

    public RobotAndTarget(double xRobot, double yRobot, double xTarget, double yTarget) {
        super(xRobot, yRobot, xTarget, yTarget);
    }
    private volatile double robotDirection = 15;

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
                (Math.cos(robotDirection  + angularVelocity * duration) - Math.cos(robotDirection));
        if (!Double.isFinite(newY))
        {
            newY = yRobot + velocity * duration * Math.sin(robotDirection);
        }
        xRobot = newX;
        yRobot = newY;
        robotDirection = ApplicationMath.asNormalizedRadians(robotDirection + angularVelocity * duration);
    }
    @Override
    public void update() {
        double distance = ApplicationMath.distance(xTarget, yTarget, xRobot, yRobot);
        if (distance < 0.5) {
            return;
        }
        double angleToTarget = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
        double angularVelocity = 0;
        if (angleToTarget > robotDirection) {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < robotDirection) {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(maxVelocity, angularVelocity, 10);
    }

    public void setTargetPosition(Point p)
    {
        xTarget = p.x;
        yTarget = p.y;
    }
}
