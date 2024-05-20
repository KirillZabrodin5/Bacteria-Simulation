package model;

import model.entity.*;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.*;

public class Model {
    private List<AbstractEntity> entitiesList = new ArrayList<>();
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
        removeEntities();
        addEntities();
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

    public void moveBacteria(Bacteria bacteria, int newX, int newY) { //тут все норм, имхо
        killEntity(bacteria);
        bacteria.setCoords(new Point(newX, newY));
        entitiesMap.put(bacteria.getCoords(), bacteria);
        freeCells.remove(bacteria.getCoords());
    }

    public void eatFood(Food food) { //здесь то же самое, что и бактерии - норм все
        killEntity(food);
        food.setCoords(getRandomFreeCellCoords());
        entitiesMap.put(food.getCoords(), food);
        freeCells.remove(food.getCoords());
    }

//    public void eatPoison(Poison poison) {
//        Food food = new Food(poison.getCoords());
//        entitiesMap.put(poison.getCoords(), food);
//        newEntities.add(food);
//        oldEntities.add(poison);
//    }

    public void eatPoison(Bacteria bacteria) {
        //entitiesMap.remove(bacteria.getCoords());
        oldEntities.add(bacteria);

        Poison poison = new Poison(bacteria.getCoords());

        entitiesMap.put(poison.getCoords(), poison);
        newEntities.add(poison);
    }

    public void killEntity(AbstractEntity entity) {
        entitiesMap.remove(entity.getCoords());
        freeCells.add(entity.getCoords());
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
        freeCells.remove(coords);
        return coords;
    }

    public AbstractEntity getEntityOnCoords(Point p) {
        return entitiesMap.get(p);
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

    public List<AbstractEntity> getEntitiesList() {
        return entitiesList;
    }
}
