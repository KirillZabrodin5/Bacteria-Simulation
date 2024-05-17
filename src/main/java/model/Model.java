package model;

import model.entity.*;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.*;

public class Model {
    private final List<AbstractEntity> entities = new ArrayList<>();
    private List<Point> freeCells = new ArrayList<>();
    private Map<Point, AbstractEntity> entitiesMap = new HashMap<>();
    public Model() {
        fillFreeCells();

        entities.add(new Bacteria(getRandomFreeCellCoords()));
        entities.add(new Bacteria(getRandomFreeCellCoords()));
        entities.add(new Bacteria(getRandomFreeCellCoords()));
        entities.add(new Food(getRandomFreeCellCoords()));
        entities.add(new Food(getRandomFreeCellCoords()));
        entities.add(new Poison(getRandomFreeCellCoords()));
        entities.add(new Poison(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));

        fillEntitiesMap();
    }

    public void updateModel() {
        //здесь надо обновлять координаты сущностей, то есть делать put каждый раз...
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
        //move(new Bacteria(1,1), Steps.DOWN, Food.class, Poison.class, Wall.class);
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
//        //если текущая сущность это бактерия, то делаем шаг и потом проверяем, свободна ли клетка в которую мы шагаем
//        //если клетка свободна, то перемещаем туда бактерию
//        //иначе через instanseof проверяем, что за объект в этой клетке
//        //если там стена, то ищем другой маршрут
//        //если там еда, то едим и генерируем новую еду
//        //если там яд, то убиваем бактерию и создаем новую
          //если там другая бактерия, то ищем новую клетку, в которую можно попасть, эта уже занята
//    }

//    private void moveBacteria(Bacteria bacteria) {
//        final Steps[] steps = Steps.values();
//        final int lenSteps = steps.length;
//
//        Random random = new Random();
//        int number = random.nextInt(lenSteps);
//
//        Steps current = steps[number];
//
//        int x = bacteria.getCoords().x + current.getX();
//        int y = bacteria.getCoords().y + current.getY();
//
//        if (isValidStepByX(x) && isValidStepByY(y)) {
//            AbstractEntity entity = getEntityOnCoords(new Point(x,y));
//            if(entity != null) {
//                if (entity instanceof Wall) {
//
//                } else if (entity instanceof Food) {
//                    eatFood((Food) entity);
//                    bacteria.setCoords(new Point(x,y));
//                } else if (entity instanceof Poison) {
//
//                }
//            }
//            getCoords().x += current.getX();
//            getCoords().y += current.getY();
//        }
//    }

    private boolean isValidStepByX(int coordinate) {
        if (coordinate < 0 || coordinate >= GameWindowConfig.getCountOfCellsInLength()) {
            return false;
        }
        return true;
    }

    private boolean isValidStepByY(int coordinate) {
        if (coordinate < 0 || coordinate >= GameWindowConfig.getCountOfCellsInWidth()) {
            return false;
        }
        return true;
    }

    private void eatFood(Food food) {
        killCell(food);
        entitiesMap.put(getRandomFreeCellCoords(), food);
    }

//    public void eatPoison(BacteriaCellEntity cell) {
//        PoisonCellEntity newPoison = new PoisonCellEntity(cell.getCoords());
//        killCell(cell);
//        entityMap.put(cell.getCoords(), newPoison);
//        newCells.add(newPoison);
//    }

    public void killCell(AbstractEntity entity) {
        entitiesMap.remove(entity.getCoords());
    }
    public Map<Point, AbstractEntity> getEntitiesMap() {
        return entitiesMap;
    }

    private void fillEntitiesMap() {
        for(AbstractEntity entity : entities) {
            entitiesMap.put(entity.getCoords(), entity);
        }
    }
    public Point getRandomFreeCellCoords() { //список свободных клеток нахуй не нужен, потому что есть map с занятыми клетками
        //сделать шаг из енама и проверить, что это клетка свободна
        int len = freeCells.size();
        Random random = new Random();
        int index = random.nextInt(len);
        Point coords = freeCells.get(index);
        removeCellFromFreeCells(coords);
        return coords;
    }

    public AbstractEntity getEntityOnCoords(Point p) {
        if (entitiesMap.containsKey(p)) {
            return entitiesMap.get(p);
        }
        return null;
    }

    /**
     * Метод для заполнения списка, элементами которого являются координаты свободных клеток
     * */
    private void fillFreeCells() {
        int countCellsInLength = GameWindowConfig.getCountOfCellsInLength();
        int countCellsInWidth = GameWindowConfig.getCountOfCellsInWidth();
        for (int i = 0; i < countCellsInLength; i++) {
            for (int j = 0; j < countCellsInWidth; j++) {
                freeCells.add(new Point(i, j));
            }
        }
    }

    private void addCellToFreeCells(Point coords) {
        freeCells.add(coords);
    }
    private void removeCellFromFreeCells(Point coords) {
        freeCells.remove(coords);
    }

    public List<AbstractEntity> getEntities() {
        return entities;
    }
}
