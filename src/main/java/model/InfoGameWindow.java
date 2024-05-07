package model;

public class InfoGameWindow { //наверное, лучше сделать все методы этого класса статиками
    private final int countOfCells = 10; //надо сделать количество клеток по вертикали, по горизонтали

    private final int lengthGameWindow = 600;

    public int countCellSizeInPixels() {
        return lengthGameWindow / countOfCells;
    }

    public int translate(int numberCell) { //из номера клетки в пиксели
        return numberCell * countCellSizeInPixels();
    }

    public int getCountOfCells() {
        return countOfCells;
    }

    public int getLengthGameWindow() {
        return lengthGameWindow;
    }

    public int getCellSize() {
        return lengthGameWindow / countOfCells;
    } //сделать float
}
