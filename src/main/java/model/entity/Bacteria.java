package model.entity;

import model.Steps;
import utils.GameWindowConfig;

import java.awt.Point;
import java.util.Random;

public class Bacteria extends AbstractEntity {
    //тут создать 2 листа с едой и ядом? - можно словарь map
    public Bacteria(Point coords) {
        super(coords);
    }

    public void moveBacteria() {
        //делать рандомный ход, если он валидный (isVald() - не врезается в стену)
        //если встретилась еда, то съесть и встать на её место и создать новую еду
        //если встретился яд, то умереть и создать новую бактерию и новый яд с рандомными координатами
        final Steps[] steps = Steps.values();
        final int lenSteps = steps.length;

        Random random = new Random();
        int number = random.nextInt(lenSteps);

        Steps current = steps[number];
        if (isValidStepByX(getCoords().x + current.getX()) && isValidStepByY(getCoords().y + current.getY())) {
            getCoords().x += current.getX();
            getCoords().y += current.getY();
        }
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

    @Override
    public void update() {
        moveBacteria();
    }
}
