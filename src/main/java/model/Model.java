package model;

import model.entity.*;
import utils.ApplicationMath;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;


public class Model
{
    public Robot robot = new Robot(300, 300);
    public Target target = new Target(100, 100);

    private final List<BaseEntity> entities = new ArrayList<>();
    public Model() {
        entities.add(robot);
        entities.add(target);
    }

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

    public List<BaseEntity> getEntities() {
        return entities;
    }
}
