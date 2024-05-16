package utils;

import java.awt.*;

public class GameWindowConfig {
    private static final int countOfCellsInLength = 8; //надо сделать количество клеток по вертикали, по горизонтали

    private static final int countOfCellsInWidth = 8;
    private static final int lengthGameWindow = 640; //длина по горизонтали

    private static final int widthGameWindow = 640;

    public static Point translate(int numberCell) { //из номера клетки в пиксели
        return new Point(numberCell * getCellSizeInLength(), numberCell * getCellSizeInWidth());
    }

    public static int getCountOfCellsInLength() {
        return countOfCellsInLength;
    }

    public static int getCountOfCellsInWidth() {
        return countOfCellsInWidth;
    }

    public static int getLengthGameWindow() {
        return lengthGameWindow;
    }

    public static int getWidthGameWindow() {
        return lengthGameWindow;
    }

    public static int getCellSizeInLength() {
        return lengthGameWindow / countOfCellsInLength;
    }

    public static int getCellSizeInWidth() {
        return widthGameWindow / countOfCellsInWidth;
    }
}
