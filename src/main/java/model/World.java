package model;

import model.entity.*;
import viewModel.GameWindowConfig;

import java.awt.Point;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {
    private final List<AbstractEntity> entitiesList = new ArrayList<>();
    private final List<AbstractEntity> newEntities = new CopyOnWriteArrayList<>();
    private final List<AbstractEntity> entitiesToRemove = new CopyOnWriteArrayList<>(); //создать класс entitiesRepository - 3 поля
    private final List<Point> freeCells = new ArrayList<>();
    private final Map<Point, AbstractEntity> entitiesMap = new HashMap<>(); //создать класс для поля (из freeCells и entitiesMap)
    private final WorldContext worldContext = new WorldContext(this);

    public World() {
        fillFreeCells();

        createEntities();

        fillEntitiesMap();
    }

    public void updateWorld() {
        for (AbstractEntity entity : entitiesList) {
            entity.update(worldContext);
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

    public void moveBacteria(Bacteria bacteria, int newX, int newY) {
        killEntity(bacteria);
        bacteria.setCoords(new Point(newX, newY));
        newEntities.add(bacteria);
        entitiesMap.put(bacteria.getCoords(), bacteria);
        freeCells.remove(bacteria.getCoords());
    }

    public void eatFood(Food food) {
        killEntity(food);
        food.setCoords(getRandomFreeCellCoords());
        newEntities.add(food);
        entitiesMap.put(food.getCoords(), food);
        freeCells.remove(food.getCoords());
    }

    public void neutralizePoison(Poison poison) {
        Food food = new Food(poison.getCoords());
        entitiesMap.put(poison.getCoords(), food);
        newEntities.add(food);
        entitiesToRemove.add(poison);
    }

    public void eatPoison(Bacteria bacteria) {
        //entitiesMap.remove(bacteria.getCoords());
        entitiesToRemove.add(bacteria);

        Poison poison = new Poison(bacteria.getCoords());

        entitiesMap.put(poison.getCoords(), poison);
        newEntities.add(poison);
    }

    public void killEntity(AbstractEntity entity) {
        entitiesMap.remove(entity.getCoords());
        freeCells.add(entity.getCoords());
        entitiesToRemove.add(entity);
    }


    public Step generateValidStep(AbstractEntity entity) {
        final Step[] steps = Step.values();
        final int lenSteps = steps.length;
        Random random = new Random();
        Step step;
        while (true) {
            int number = random.nextInt(lenSteps);
            step = steps[number];
            if (isValidStepByX(entity.getCoords().x + step.getX()) &&
                    isValidStepByY(entity.getCoords().y + step.getY())) {
                break;
            }
        }
        return step;
    }

    private boolean isValidStepByX(int x) {
        return x >= 0 && x < GameWindowConfig.getCountOfCellsInLength();
    }

    private boolean isValidStepByY(int y) {
        return y >= 0 && y < GameWindowConfig.getCountOfCellsInWidth();
    }

    public void addEntities() {
        entitiesList.addAll(newEntities);

        newEntities.clear();
    }

    public void removeEntities() {
        entitiesList.removeAll(entitiesToRemove);

        entitiesToRemove.clear();
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
        return new ArrayList<>(entitiesList);
    }
}
