package model;

import model.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Model {
    private final List<AbstractEntity> entities = new ArrayList<>();
    public Model() {
        entities.add(new Bacteria(2, 3));
        entities.add(new Food(2, 2));
        entities.add(new Poison(2, 4));
    }

    public void updateModel() {
        for (AbstractEntity entity : entities) {
            entity.update();
        }
        //move() из класса modelContext
//        if (bacteria.x == food.x && bacteria.y == food.y) {
//            food.createNewInstance();
//        }
//
//        if (bacteria.x == poison.x && bacteria.y == poison.y) {
//            bacteria.createNewInstance();
//            poison.createNewInstance();
//        }
    }

    public List<AbstractEntity> getEntities() {
        return entities;
    }
}
