package view;

import model.InfoGameWindow;
import viewModel.ViewModel;

import java.awt.Graphics;
import java.awt.Color;

public final class Grid {
    public static void drawGrid(Graphics g) {
        InfoGameWindow infoGameWindow = new InfoGameWindow();
        g.setColor(Color.WHITE);
        int countOfCells = infoGameWindow.getCountOfCells();
        int cellSize = infoGameWindow.getCellSize();

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
