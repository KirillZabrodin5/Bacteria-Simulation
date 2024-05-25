package model;

import model.entity.AbstractEntity;
import model.entity.Bacteria;
import model.entity.Food;
import model.entity.Poison;

import java.awt.*;

/**
 * Если я правильно понял, то этот класс должен просто инкапсулировать модель,
 * то есть порвать возможность взаимодействовать
 * с моделью напрямую. То есть просто сделать тут методы для вызова методов модели
 * ...
 * Этот класс нужен для того, чтобы содержать методы для создания, изменения и удаления экземпляров модели.
 * Например, методы createEntity, updateEntity, deleteEntity
 * могут быть реализованы для управления сущностями в модели.
 */

public class WorldContext {
    private final World world;

    public WorldContext(World model) {
        this.world = model;
    }

    public boolean isValidStep(AbstractEntity entity, Direction step) {
        return world.isValidStep(entity, step);
    }

    /**
     * Этот метод нужен для реализации движения всех entities
     *
     * @param entity
     * @param step
     * @param availableEntitiesToMove
     * @return возвращает entity, на которую наступила бактерия, либо null, если не наступила
     */
    @SafeVarargs
    public final AbstractEntity checkCellForAnEntity(AbstractEntity entity, Direction step,
                                                     Class<? extends AbstractEntity>... availableEntitiesToMove) {
        //добавить перемещение бактерии на новые координаты
        int newX = entity.getCoords().x + step.getX();
        int newY = entity.getCoords().y + step.getY();
        AbstractEntity entityOnCoords = world.getEntityOnCoords(new Point(newX, newY));
        for (Class<? extends AbstractEntity> aClass : availableEntitiesToMove) {
            if (aClass.isInstance(entityOnCoords)) {
                return entityOnCoords;
            }
        }
        return null;
    }

    //переместиться, съесть и узнать, что находится в указанном направлении - добавить эти методы
    //как будто у меня все эти 3 метода есть, возможно стоит пересмотреть архитектуру и организацию этих моментов
    //заняться этим, если будет время, то есть после того, как напишу мозг бактерии
    public void moveBacteria(Bacteria bacteria, int newX, int newY) {
        world.moveBacteria(bacteria, newX, newY);
    }

    //создать eat(Bacteria, Direction) - общий метод для еды и яда, не понял зачем

    public void eatFood(Food food) {
        world.eatFood(food);
    }

    public void eatPoison(Bacteria bacteria) {
        world.eatPoison(bacteria);
    }

    public void neutralizePoison(Poison poison) {
        world.neutralizePoison(poison);
    }

    public void killCell(AbstractEntity entity) {
        world.killEntity(entity);
    }

    public void createChild(Bacteria bacteria) {
        world.createChild(bacteria);
    }
}
