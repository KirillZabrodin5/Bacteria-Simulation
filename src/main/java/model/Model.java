package model;

import model.entity.*;
import view.PaintEntity;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Model extends JPanel
{
//    private static volatile double m_robotPositionX = 300;
//
//    private static volatile double m_robotPositionY = 300;
//
//    private static volatile double m_robotDirection = 0;
//
//    private static volatile int m_targetPositionX = 100;
//
//    private static volatile int m_targetPositionY = 100;
//
//    private static final double maxVelocity = 0.1;
//    private static final double maxAngularVelocity = 0.001;
    public Robot robot = new Robot();
    public Target target = new Target();

    //отсюда и до сеттера надо подумать, куда раскидать, но вроде это не модел
    private final Timer m_timer = new Timer("events generator", true);
    public Model()
    {//viewModel
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter() // это во вью модел, потому что происходит обработка нажатий мыши
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                target.setTargetPosition(e.getPoint());
                //repaint();
            }
        });

        setDoubleBuffered(true);
    }

    protected void onRedrawEvent() //  во вью наверное, потому что это перерисовка
    {
        EventQueue.invokeLater(this::repaint);
    }
    // то, что ниже точно в модель


    protected void onModelUpdateEvent()
    {
        double distance = ApplicationMath.distance(target.getTargetPositionX(), target.getTargetPositionY(),
            robot.getRobotPositionX(), robot.getRobotPositionY());
        if (distance < 0.5)
        {
            return;
        }
        double velocity = robot.getMaxVelocity();
        double angleToTarget = ApplicationMath.angleTo(robot.getRobotPositionX(), robot.getRobotPositionY(),
                target.getTargetPositionX(), target.getTargetPositionY());
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
