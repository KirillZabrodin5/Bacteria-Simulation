package model;

import model.entity.*;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.*;

public class Model {
    private final List<AbstractEntity> entitiesList = new ArrayList<>();
    private List<AbstractEntity> newEntities = new ArrayList<>();
    private List<AbstractEntity> oldEntities = new ArrayList<>();
    private List<Point> freeCells = new ArrayList<>();
    private Map<Point, AbstractEntity> entitiesMap = new HashMap<>();
    private ModelContext modelContext = new ModelContext(this);

    public Model() {
        fillFreeCells();

        createEntities();

        fillEntitiesMap();
    }

    public void updateModel() {
        for (AbstractEntity entity : entitiesList) {
            entity.update(modelContext);
        }
        System.out.println("Длина entitiesList до: " + entitiesList.size());
        removeEntities();
        addEntities();
        System.out.println("Длина entitiesList после: " + entitiesList.size());
    }

    private void createEntities() {
        entitiesList.add(new Bacteria(getRandomFreeCellCoords()));
        entitiesList.add(new Bacteria(getRandomFreeCellCoords()));
        entitiesList.add(new Bacteria(getRandomFreeCellCoords()));
        entitiesList.add(new Bacteria(getRandomFreeCellCoords()));
        entitiesList.add(new Bacteria(getRandomFreeCellCoords()));

        entitiesList.add(new Food(getRandomFreeCellCoords()));
        entitiesList.add(new Food(getRandomFreeCellCoords()));
        entitiesList.add(new Food(getRandomFreeCellCoords()));
        entitiesList.add(new Food(getRandomFreeCellCoords()));
        entitiesList.add(new Food(getRandomFreeCellCoords()));

        entitiesList.add(new Poison(getRandomFreeCellCoords()));
        entitiesList.add(new Poison(getRandomFreeCellCoords()));
        entitiesList.add(new Poison(getRandomFreeCellCoords()));
        entitiesList.add(new Poison(getRandomFreeCellCoords()));
        entitiesList.add(new Poison(getRandomFreeCellCoords()));

        entitiesList.add(new Wall(getRandomFreeCellCoords()));
        entitiesList.add(new Wall(getRandomFreeCellCoords()));
        entitiesList.add(new Wall(getRandomFreeCellCoords()));
        entitiesList.add(new Wall(getRandomFreeCellCoords()));
        entitiesList.add(new Wall(getRandomFreeCellCoords()));
    }

//    public final void move(AbstractEntity entity, Steps step) {
//        if (entity instanceof Bacteria) {
//            int oldX = entity.getCoords().x;
//            int oldY = entity.getCoords().y;
//            int newX = entity.getCoords().x + step.getX();
//            int newY = entity.getCoords().y + step.getY();
//            AbstractEntity entityOnCoords = getEntityOnCoords(new Point(newX, newY));
//            if (entityOnCoords == null) {
//                moveBacteria(entity, oldX, oldY, newX, newY);
//            } else {
//                if (entityOnCoords instanceof Wall || entityOnCoords instanceof Bacteria) {
//                    //надо ли что-нибудь в этом if делать?
//                } else if (entityOnCoords instanceof Food) {
//                    moveBacteria(entity, oldX, oldY, newX, newY);
//                    eatFood((Food) entityOnCoords);
//
//                } else if (entityOnCoords instanceof Poison) {
//                    eatPoison((Poison) entityOnCoords);
//                    killCell(entity);
//                }
//            }
//        }
//    }

    public void moveBacteria(Bacteria bacteria, int oldX, int oldY, int newX, int newY) {
        killEntity(bacteria);

        bacteria.setCoords(new Point(newX, newY));

        entitiesMap.put(bacteria.getCoords(), bacteria);
        freeCells.remove(bacteria.getCoords());
    }

    public Steps generateValidStep(AbstractEntity entity) {
        //мб здесь делать проверку, что в новых координтах нет стены или другой бактерии?
        final Steps[] steps = Steps.values();
        final int lenSteps = steps.length;
        Random random = new Random();
        Steps current;
        while (true) {
            int number = random.nextInt(lenSteps);
            current = steps[number];
            if (isValidStepByX(entity.getCoords().x + current.getX()) &&
                    isValidStepByY(entity.getCoords().y + current.getY())) {
                break;
            }
        }
        return current;
    }

    public void eatFood(Food food) {
        killEntity(food);
        Point newCoords = getRandomFreeCellCoords();
        food.setCoords(newCoords);
        entitiesMap.put(newCoords, food);
        freeCells.remove(newCoords);
    }

//    public void eatPoison(Poison poison) {
//        Food food = new Food(poison.getCoords());
//        entitiesMap.put(poison.getCoords(), food);
//        newEntities.add(food);
//        oldEntities.add(poison);
//    }

    public void eatPoison(Bacteria bacteria) {
        killEntity(bacteria);
        Poison poison = new Poison(bacteria.getCoords());
        entitiesMap.put(poison.getCoords(), poison);
        newEntities.add(poison);
        oldEntities.add(bacteria);
    }

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


    public void killEntity(AbstractEntity entity) {
        entitiesMap.remove(entity.getCoords());
        freeCells.add(entity.getCoords());
        //oldEntities.add(entity);
    }

    public void addEntities() {
        entitiesList.addAll(newEntities);

        newEntities.clear();
    }

    public void removeEntities() {
        entitiesList.removeAll(oldEntities);

        oldEntities.clear();
    }

    public Map<Point, AbstractEntity> getEntitiesMap() {
        return entitiesMap;
    }

    private void fillEntitiesMap() {
        for (AbstractEntity entity : entitiesList) {
            entitiesMap.put(entity.getCoords(), entity);
        }
    }

    public Point getRandomFreeCellCoords() {
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
     */
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

    public List<AbstractEntity> getEntitiesList() {
        return entitiesList;
    }
}
