package model;

import model.entity.AbstractEntity;
import model.entity.Bacteria;
import model.entity.Food;
import model.entity.Poison;

import java.awt.Point;

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


    public Steps generateValidStep(AbstractEntity entity) {
        return model.generateValidStep(entity);
    }

        //Была предложена такая сигнатура метода move, но я не понял для чего и как этот метод реализовать
    /**
     * Этот метод нужен для реализации движения всех entities
     *
     * @param entity
     * @param step
     * @param availableEntitiesToMove
     * @return возвращает entity, на которую наступила бактерия, либо null, если не наступила
     */
    @SafeVarargs
    public final AbstractEntity move(AbstractEntity entity, Steps step,
                           Class<? extends AbstractEntity>... availableEntitiesToMove) {
        int newX = entity.getCoords().x + step.getX();
        int newY = entity.getCoords().y + step.getY();
        AbstractEntity entityOnCoords = model.getEntityOnCoords(new Point(newX, newY));
        for (Class<? extends AbstractEntity> aClass : availableEntitiesToMove) {
           if (aClass.isInstance(entityOnCoords)) {
               return entityOnCoords;
           }
        }
        return null;
    }

    public void moveBacteria(Bacteria bacteria, int oldX, int oldY, int newX, int newY) {
        model.moveBacteria(bacteria, oldX, oldY, newX, newY);
    }

    public void eatFood(Food food){
        model.eatFood(food);
    }

    public void eatPoison(Bacteria bacteria){
        model.eatPoison(bacteria);
    }

    public void killCell(AbstractEntity entity) {
        model.killEntity(entity);
    }
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
