package model.entity;

import utils.ApplicationMath;

import java.awt.*;

public class Robot extends AbstractEntity {

    public Robot(int xRobot, int yRobot) {
        super(xRobot, yRobot);
    }
    private volatile double robotDirection = 0;

    private final double maxVelocity = 0.1;

    public void moveRobot(double velocity, double duration) {
        velocity = ApplicationMath.applyLimits(velocity, 0, maxVelocity);

        // Вычисляем новые координаты робота
        double newX = x + velocity * duration * Math.cos(robotDirection);
        double newY = y + velocity * duration * Math.sin(robotDirection);

        // Проверяем, не выходит ли робот за границы поля
        if (newX >= 0 && newX < 50*10 && newY >= 0 && newY < 50*10) {
            x = (int) newX;
            y = (int) newY;
            robotDirection = ApplicationMath.angleTo(x, y, xTarget, yTarget);
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
        double angleToTarget = ApplicationMath.angleTo(xRobot, yRobot, xTarget, yTarget);
        double angularVelocity = 0;
        double maxAngularVelocity = 0.001;
        if (angleToTarget > robotDirection) {
            angularVelocity = maxAngularVelocity;
        }
        if (angleToTarget < robotDirection) {
            angularVelocity = -maxAngularVelocity;
        }

        moveRobot(maxVelocity, angularVelocity, 15);

        double distance = ApplicationMath.distance(xTarget, yTarget, xRobot, yRobot);
        if (distance < sizeRobot) {
            // Генерируем новое случайное местоположение для цели
            xTarget = (int) (Math.random() * (50*10 / sizeRobot)) * sizeRobot;
            yTarget = (int) (Math.random() * (50*10 / sizeRobot)) * sizeRobot;
        }
    }
}
