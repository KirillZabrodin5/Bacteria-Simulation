package model;

import model.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Model {
    private final List<AbstractEntity> entities = new ArrayList<>();

    public Model() {
        Bacteria robot = new Bacteria(2, 2); //начиная с 2, 2 считаются неправильно координаты. В чем проблема
        Food target = new Food(7, 7);
        Poison poison = new Poison(0,0);
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
