package model;

import model.entity.*;

import utils.ApplicationMath;
import javax.swing.*;
import java.awt.EventQueue;
import java.util.List;
import java.util.ArrayList;


public class Model extends JPanel
{
    public Robot robot = new Robot(300, 300);
    public Target target = new Target(100, 100);

    private List<BaseEntity> entities = new ArrayList<>();
    public Model() {
        entities.add(robot);
        entities.add(target);
    }

    //отсюда и до сеттера надо подумать, куда раскидать, но вроде это не модел

//    public Model()
//    {//viewModel
//        m_timer.schedule(new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                onRedrawEvent();
//            }
//        }, 0, 50);
//        m_timer.schedule(new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                onModelUpdateEvent();
//            }
//        }, 0, 10);
//        addMouseListener(new MouseAdapter() // это во вью модел, потому что происходит обработка нажатий мыши
//        {
//            @Override
//            public void mouseClicked(MouseEvent e)
//            {
//                target.setTargetPosition(e.getPoint());
//                //repaint();
//            }
//        });
//
//        setDoubleBuffered(true);
//    }

    public List<BaseEntity> getEntities() {
        return entities;
    }

    protected void onRedrawEvent() //  во вью наверное, потому что это перерисовка
    {
        EventQueue.invokeLater(this::repaint);
    }
    // то, что ниже точно в модель

    public void updateModel()
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

}
