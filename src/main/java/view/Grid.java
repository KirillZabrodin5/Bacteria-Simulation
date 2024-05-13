package view;

import utils.GameWindowConfig;

import java.awt.Graphics;
import java.awt.Color;

public final class Grid {
    public static void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);
        int countOfCells = GameWindowConfig.getCountOfCells();
        int cellSize = GameWindowConfig.getCellSize();

        // Рисуем вертикальные линии
        for (int i = 0; i <= countOfCells; i++) {
            g.drawLine(cellSize * i, 0, cellSize * i, countOfCells * cellSize);
        }

        // Рисуем горизонтальные линии
        for (int i = 0; i <= countOfCells; i++) {
            g.drawLine(0, cellSize * i, countOfCells * cellSize, cellSize * i);
        }
    }
}
