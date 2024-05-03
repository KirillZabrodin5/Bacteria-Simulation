package model.entity;

import RunApplication.Main;
import model.Steps;
import utils.ApplicationMath;

import java.awt.*;

public class RobotAndTarget extends AbstractEntity {

    public RobotAndTarget(int xRobot, int yRobot, int xTarget, int yTarget, int sizeRobot) {
        super(xRobot, yRobot, xTarget, yTarget, sizeRobot);
    }
    private volatile double robotDirection = 0;

    private final double maxVelocity = 0.1;

    private final double maxAngularVelocity = 0.001;

    public void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);

        // Вычисляем новые координаты робота
        double newX = xRobot + velocity * duration * Math.cos(robotDirection);
        double newY = yRobot + velocity * duration * Math.sin(robotDirection);

        // Проверяем, не выходит ли робот за границы поля
        if (newX >= 0 && newX < Main.getWidthGameWindow() && newY >= 0 && newY < Main.getHeightGameWindow()) {
            xRobot = (int) newX;
            yRobot = (int) newY;
            robotDirection = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
        }
//        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);
//        angularVelocity = ApplicationMath.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
//        double newX = xRobot + velocity / angularVelocity *
//                (Math.sin(robotDirection  + angularVelocity * duration) -
//                        Math.sin(robotDirection));
//        if (!Double.isFinite(newX))
//        {
//            newX = xRobot + velocity * duration * Math.cos(robotDirection);
//        }
//        double newY = yRobot - velocity / angularVelocity *
//                (Math.cos(robotDirection  + angularVelocity * duration) - Math.cos(robotDirection));
//        if (!Double.isFinite(newY))
//        {
//            newY = yRobot + velocity * duration * Math.sin(robotDirection);
//        }
//        xRobot = (int) newX;
//        yRobot = (int) newY;
//        robotDirection = ApplicationMath.asNormalizedRadians(robotDirection + angularVelocity * duration);
    }
    @Override
    public void update() {
//        double distance = ApplicationMath.distance(xTarget, yTarget, xRobot, yRobot);
//        if (distance < 0.5) {
//            return;
//        }
//        double angleToTarget = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
//        double angularVelocity = 0;
//        if (angleToTarget > robotDirection) {
//            angularVelocity = maxAngularVelocity;
//        }
//        if (angleToTarget < robotDirection) {
//            angularVelocity = -maxAngularVelocity;
//        }
        double angleToTarget = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
        double angularVelocity = 0;
        if (angleToTarget > robotDirection) {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < robotDirection) {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(maxVelocity, angularVelocity, 15);

        double distance = ApplicationMath.distance(xTarget, yTarget, xRobot, yRobot);
        if (distance < sizeRobot/2) {
            // Генерируем новое случайное местоположение для цели
            xTarget = (int) (Math.random() * (Main.getWidthGameWindow() / sizeRobot)) * sizeRobot;
            yTarget = (int) (Math.random() * (Main.getHeightGameWindow() / sizeRobot)) * sizeRobot;
        }


    }

    public void setTargetPosition(Point p)
    {
        xTarget = p.x;
        yTarget = p.y;
    }
}
