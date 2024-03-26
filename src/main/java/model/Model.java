package model;

import model.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Model
{
    public RobotAndTarget robotAndTarget = new RobotAndTarget(300, 300, 100, 100);
    private final List<BaseEntity> entities = new ArrayList<>();
    public Model() {
        entities.add(robotAndTarget);
    }

    public void updateModel()
    {
        robotAndTarget.update();
    }

    public List<BaseEntity> getEntities() {
        return entities;
    }
}
