package model;

import model.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Model {
    private final List<AbstractEntity> entities = new ArrayList<>();

    public Model() {
        Bacteria robot = new Bacteria(2, 3);
        Food target = new Food(2, 2);
        Poison poison = new Poison(2,4);
        entities.add(robot);
        entities.add(target);
        entities.add(poison);
    }

    public void updateModel() {
        for (AbstractEntity entity : entities) {
            entity.update();
        }
    }

    public List<AbstractEntity> getEntities() {
        return entities;
    }
}
