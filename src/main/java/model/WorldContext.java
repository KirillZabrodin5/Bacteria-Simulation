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

    public WorldContext(World world) {
        this.world = world;
    }

    public boolean isValidStep(AbstractEntity entity, Direction step) {
        return world.isValidStep(entity, step);
    }

    /**
     * Этот метод нужен для того, чтобы узнать, что находится в клетке, в которую хочет сходить бактерия
     * @return возвращает entity, на которую наступит бактерия, либо null, если в клетке пусто
     */
    @SafeVarargs
    public final AbstractEntity getEntityOnCoords(AbstractEntity entity, Direction step,
                                                  Class<? extends AbstractEntity>... availableEntitiesToMove) {
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

    public void moveBacteria(Bacteria bacteria, int newX, int newY) {
        world.moveBacteria(bacteria, newX, newY);
    }

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
