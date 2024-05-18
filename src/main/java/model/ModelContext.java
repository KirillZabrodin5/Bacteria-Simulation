package model;


import model.entity.AbstractEntity;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.*;

/**
 * Если я правильно понял, то этот класс должен просто инкапсулировать модель,
 * то есть порвать возможность взаимодействовать
 * с моделью напрямую. То есть просто сделать тут методы для вызова методов модели
 * ...
 * Этот класс нужен для того, чтобы содержать методы для создания, изменения и удаления экземпляров модели.
 * Например, методы createEntity, updateEntity, deleteEntity
 * могут быть реализованы для управления сущностями в модели.
 */

public class ModelContext {
    private final Model model;

    public ModelContext(Model model) {
        this.model = model;
    }

//
//    public void setNewCoordinates(AbstractEntity entity) {
//        x = (int) (Math.random() * GameWindowConfig.getCountOfCells());
//        y = (int) (Math.random() * GameWindowConfig.getCountOfCells());
//    }
    //проверять, что координаты свободные через while(true) пока не получится
    //либо создать список со свободными клетками

//    private boolean isEmptyCell(Point coords) {
//        return occupiedCells.containsKey(coords);
//    }
}
