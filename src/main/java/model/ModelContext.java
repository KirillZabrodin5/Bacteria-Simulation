package model;


import model.entity.AbstractEntity;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Этот класс нужен для того, чтобы содержать методы для создания, изменения и удаления экземпляров модели.
 * Например, методы createEntity, updateEntity, deleteEntity
 * могут быть реализованы для управления сущностями в модели.
 */

public class ModelContext {
    private final Model model;
    private List<Point> freeCells = new ArrayList<>();

    private Map<Point, Class<AbstractEntity>> occupiedCells = new HashMap<>();

    public ModelContext(Model model) {
        this.model = model;

        int countCellsInLength = GameWindowConfig.getCountOfCellsInLength();
        int countCellsInWidth = GameWindowConfig.getCountOfCellsInWidth();
        for (int i = 0; i < countCellsInLength; i++) {
            for (int j = 0; j < countCellsInWidth; j++) {
                    freeCells.add(new Point(i,j));
            }
        }
    }

//    /**
//     * Этот метод нужен для реализации движения всех entities
//     *
//     * @param entity
//     * @param step
//     * @param availableEntitiesToMove
//     * @return возвращает entity, на которую наступила бактерия, либо null, если не наступила
//     */
//    @SafeVarargs
//    public final AbstractEntity move(AbstractEntity entity, Steps step,
//                                     Class<? extends AbstractEntity>... availableEntitiesToMove) {
//
//    }
//
//    public void setNewCoordinates(AbstractEntity entity) {
//        x = (int) (Math.random() * GameWindowConfig.getCountOfCells());
//        y = (int) (Math.random() * GameWindowConfig.getCountOfCells());
//    }
    //проверять, что координаты свободные через while(true) пока не получится
    //либо создать список со свободными клетками

    private boolean isEmptyCell(Point coords) {
        return occupiedCells.get(coords) != null;
    }
}
