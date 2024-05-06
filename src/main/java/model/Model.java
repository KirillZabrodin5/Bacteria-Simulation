package model;

import model.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Model
{
    private final List<AbstractEntity> entities = new ArrayList<>();
    public Model() {
        Robot robotAndTarget = new Robot(100, 400, 200, 200, 50);
        //убрать size in ViewModel
        entities.add(robotAndTarget);
    }

    public void updateModel()
    {
        for(AbstractEntity entity : entities) {
            entity.update();
        }
    }
    public List<AbstractEntity> getEntities() {
        return entities;
    }
    //размеры окна в клетках добавить get
}
