package view;

import viewModel.MainApplicationFrame;

import java.awt.Graphics;
import java.awt.Color;

public final class Grid {
    public static void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);
        int countOfCellsHorizontally = 10;
        int countOfCellsVertically = 10;
        int sizeCell = MainApplicationFrame.WIDTH_GAME_WINDOW / countOfCellsVertically;

        // Рисуем вертикальные линии
        for (int i = 0; i <= countOfCellsHorizontally; i++) {
            g.drawLine(sizeCell * i, 0, sizeCell * i, countOfCellsVertically * sizeCell);
        }

        // Рисуем горизонтальные линии
        for (int i = 0; i <= countOfCellsVertically; i++) {
            g.drawLine(0, sizeCell * i, countOfCellsHorizontally * sizeCell, sizeCell * i);
        }
    }
}
