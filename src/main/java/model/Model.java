package model;

import model.entity.*;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.*;

public class Model {
    private final List<AbstractEntity> entities = new ArrayList<>();
    private List<AbstractEntity> newEntities = new ArrayList<>();
    private List<AbstractEntity> oldEntities = new ArrayList<>();
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
        entities.add(new Poison(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));
        entities.add(new Wall(getRandomFreeCellCoords()));

        fillEntitiesMap();
    }

    public void updateModel() {
        for (AbstractEntity entity : entities) {
            move(entity, generateValidStep(entity), Bacteria.class, Food.class, Poison.class, Wall.class);
        }

        removeEntities();
        addEntities();
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
    public final void move(AbstractEntity entity, Steps step,
                                     Class<? extends AbstractEntity>... availableEntitiesToMove) {
        if (entity instanceof Bacteria) {
            //write logic
            int oldX = entity.getCoords().x;
            int oldY = entity.getCoords().y;
            int newX = entity.getCoords().x + step.getX();
            int newY = entity.getCoords().y + step.getY();
            AbstractEntity entityOnCoords = getEntityOnCoords(new Point(newX, newY));
            if (entityOnCoords != null) {
                if (entityOnCoords instanceof Wall || entityOnCoords instanceof Bacteria) { //подумать чо в этом ифе делать
                    //generateValidStep(entity);
                    //move(entity, generateValidStep(entity), Bacteria.class, Food.class, Poison.class, Wall.class);
                    //надо перейти в начало цикла как-то
                }
                else if (entityOnCoords instanceof Food) { //этот if работает ахуенно, остальные пока не работают
                    eatFood((Food) entityOnCoords); //эта функция норм, мне нравится
                    entitiesMap.remove(new Point(oldX, oldY)); //3 строчки начиная с этой решают задачу, но их бы
                    freeCells.add(new Point(oldX, oldY));
                    entity.setCoords(new Point(newX, newY)); //переписать в более лучший вид и вынести в метод
                    entitiesMap.put(entity.getCoords(), entity);
                    freeCells.remove(entity.getCoords());
                } else if (entityOnCoords instanceof Poison) { //здесь меня ебут тем, что я изменяю list при итерировании по нему
                    //надо как-то это исправить
                    eatPoison((Poison) entityOnCoords);
                    entitiesMap.remove(new Point(oldX, oldY));
                    oldEntities.add(entity);

                    Bacteria bacteria = new Bacteria(getRandomFreeCellCoords());
                    newEntities.add(bacteria);
                    entitiesMap.put(bacteria.getCoords(), bacteria);
                    freeCells.remove(bacteria.getCoords());
                    //здесь надо бактерии присвоить новые координаты, типа создание новой бактерии и смерть старой
                    //и реализовать метод eatPoison
                }
            } else {
                entitiesMap.remove(entity.getCoords());
                freeCells.add(entity.getCoords());
                entity.setCoords(new Point(newX, newY));
                entitiesMap.put(entity.getCoords(),entity);
                freeCells.remove(entity.getCoords());
            }

        } else {
            //ignore
        }


        //если текущая сущность это бактерия, то делаем шаг и потом проверяем, свободна ли клетка в которую мы шагаем
        //если клетка свободна, то перемещаем туда бактерию
        //иначе через instanseof проверяем, что за объект в этой клетке
        //если там стена, то ищем другой маршрут
        //если там еда, то едим и генерируем новую еду
        //если там яд, то убиваем бактерию и создаем новую
        //если там другая бактерия, то ищем новую клетку, в которую можно попасть, эта уже занята
    }

    /**
    * Вызывать этот метод, если текущая entity = bacteria
    * */
    public void move(Bacteria bacteria) {
        final Steps[] steps = Steps.values();
        final int lenSteps = steps.length;

        Random random = new Random();
        int newX, newY;
        int attempt = 0; //это номер попытки сгенерировать новый ход - если он будет равен количество ходов,
        // то бактерия остается замкнута в стенах - можно заменить цикл на фор и эта переменная не нужна будет

        for (int i = 0; i < lenSteps; i++) {
            int number = random.nextInt(lenSteps);

            Steps current = steps[number];
            int oldX = bacteria.getCoords().x;
            int oldY = bacteria.getCoords().y;
            newX = oldX + current.getX();
            newY = oldY + current.getY();
            if (isValidStepByX(newX) && isValidStepByY(newY)) {
                AbstractEntity entity = getEntityOnCoords(new Point(newX, newY));
                if (entity != null) {
                    if (entity instanceof Wall || entity instanceof Bacteria) {
                        System.out.println("Почти врезались в стену");

                    }
                    else if (entity instanceof Food) {
                        eatFood((Food) entity); //эта функция норм, мне нравится
                        entitiesMap.remove(new Point(oldX, oldY)); //3 строчки начиная с этой решают задачу, но их бы
                        freeCells.add(new Point(oldX, oldY));
                        bacteria.setCoords(new Point(newX, newY)); //переписать в более лучший вид и вынести в метод
                        entitiesMap.put(bacteria.getCoords(), bacteria);
                        freeCells.remove(bacteria.getCoords());
                        break;
                    } else if (entity instanceof Poison) {
                        entitiesMap.remove(new Point(oldX, oldY));
                        entities.remove(bacteria);
                        break;
                        //здесь надо бактерии присвоить новые координаты, типа создание новой бактерии и смерть старой
                        //и реализовать метод eatPoison
                    }
                }
                else {
                    entitiesMap.remove(bacteria.getCoords());
                    freeCells.add(bacteria.getCoords());
                    bacteria.setCoords(new Point(newX, newY));
                    entitiesMap.put(bacteria.getCoords(), bacteria);
                    freeCells.remove(bacteria.getCoords());
                }
            }
        }
    }

    private Steps generateValidStep(AbstractEntity entity) {
        final Steps[] steps = Steps.values();
        final int lenSteps = steps.length;
        Random random = new Random();
        Steps current;
        while (true) {
            int number = random.nextInt(lenSteps);
            current = steps[number];
            if (isValidStepByX(entity.getCoords().x + current.getX()) &&
                    isValidStepByX(entity.getCoords().y + current.getY())) {
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

    private void eatFood(Food food) {

        killCell(food);
        Point newCoords = getRandomFreeCellCoords();
        food.setCoords(newCoords);
        entitiesMap.put(newCoords, food);
        freeCells.remove(newCoords);
    }

    public void eatPoison(Poison poison) {
//        entitiesMap.remove(entity.getCoords());
//        freeCells.add(entity.getCoords());
//        entity.setCoords(new Point(newX, newY));
//        entitiesMap.put(entity.getCoords(),entity);
//        freeCells.remove(entity.getCoords());

        killCell(poison);

        Food food = new Food(poison.getCoords());
        entitiesMap.put(poison.getCoords(), food);
        newEntities.add(food);
        oldEntities.add(poison);
    }

    public void killCell(AbstractEntity entity) {
        entitiesMap.remove(entity.getCoords());
        freeCells.add(entity.getCoords());
    }

    public void addEntities(){
        for(AbstractEntity entity : newEntities) {
            entities.add(entity);
        }
    }
    public void removeEntities() {
        for(AbstractEntity entity : oldEntities) {
            entities.remove(entity);
        }
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
