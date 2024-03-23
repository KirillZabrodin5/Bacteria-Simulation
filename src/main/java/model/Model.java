package model;

import model.entity.*;
import view.PaintEntity;

import utils.ApplicationMath;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Model extends JPanel
{

    public Robot robot = new Robot();
    public Target target = new Target();

    //отсюда и до сеттера надо подумать, куда раскидать, но вроде это не модел


    protected void onRedrawEvent() //  во вью наверное, потому что это перерисовка
    {
        EventQueue.invokeLater(this::repaint);
    }
    // то, что ниже точно в модель

    public List<BaseEntity> getEntities() {
        return List.of(robot, target);
    }

    protected void updateModels()
    {
        double distance = ApplicationMath.distance(target.x, target.y,
            robot.x, robot.y);
        if (distance < 0.5)
        {
            return;
        }
        double velocity = robot.getMaxVelocity();
        double angleToTarget = ApplicationMath.angleTo(robot.x, robot.y,
                target.x, target.y);
        double angularVelocity = 0;
        if (angleToTarget > robot.getRobotDirection())
        {
            angularVelocity = robot.getMaxAngularVelocity();
        }
        if (angleToTarget < robot.getRobotDirection())
        {
            angularVelocity = -robot.getMaxAngularVelocity();
        }
        
        robot.moveRobot(velocity, angularVelocity, 10);
    }
//    private void moveRobot(double velocity, double angularVelocity, double duration)
//    {
//        velocity = applyLimits(velocity, 0, maxVelocity);
//        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
//        double newX = m_robotPositionX + velocity / angularVelocity *
//            (Math.sin(m_robotDirection  + angularVelocity * duration) -
//                Math.sin(m_robotDirection));
//        if (!Double.isFinite(newX))
//        {
//            newX = m_robotPositionX + velocity * duration * Math.cos(m_robotDirection);
//        }
//        double newY = m_robotPositionY - velocity / angularVelocity *
//            (Math.cos(m_robotDirection  + angularVelocity * duration) -
//                Math.cos(m_robotDirection));
//        if (!Double.isFinite(newY))
//        {
//            newY = m_robotPositionY + velocity * duration * Math.sin(m_robotDirection);
//        }
//        m_robotPositionX = newX;
//        m_robotPositionY = newY;
//        double newDirection = asNormalizedRadians(m_robotDirection + angularVelocity * duration);
//        m_robotDirection = newDirection;
//    }



//    public static double getM_robotPositionX() {
//        return m_robotPositionX;
//    }
//
//    public static double getM_robotPositionY() {
//        return m_robotPositionY;
//    }
//
//    public static double getM_robotDirection() {
//        return m_robotDirection;
//    }
//
//    public static int getM_targetPositionX() {
//        return m_targetPositionX;
//    }
//
//    public static int getM_targetPositionY() {
//        return m_targetPositionY;
//    }
}
