package utils;

public class GameWindowConfig { //наверное, лучше сделать все методы этого класса статиками
    private static final int countOfCells = 10; //надо сделать количество клеток по вертикали, по горизонтали

    private static final int lengthGameWindow = 600;

    public static int countCellSizeInPixels() {
        return lengthGameWindow / countOfCells;
    }

    public static int translate(int numberCell) { //из номера клетки в пиксели
        return numberCell * countCellSizeInPixels();
    }

    public static int getCountOfCells() {
        return countOfCells;
    }

    public static int getLengthGameWindow() {
        return lengthGameWindow;
    }

    public static int getCellSize() {
        return lengthGameWindow / countOfCells;
    } //сделать float
}
