package view.renderers;

import viewModel.windows.GameWindowConfig;

import java.awt.Graphics;
import java.awt.Color;

public class RendererGrid {
    public void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);
        int countOfCellsInLength = GameWindowConfig.getCountOfCellsInLength();
        int countOfCellsInWidth = GameWindowConfig.getCountOfCellsInWidth();

        int cellSizeInLength = GameWindowConfig.getCellSizeInLength();
        int cellSizeInWidth = GameWindowConfig.getCellSizeInWidth();

        // Рисуем вертикальные линии
        for (int i = 0; i <= countOfCellsInLength; i++) {
            g.drawLine(cellSizeInLength * i, 0, cellSizeInLength * i, countOfCellsInWidth * cellSizeInWidth);
        }

        // Рисуем горизонтальные линии
        for (int i = 0; i <= countOfCellsInWidth; i++) {
            g.drawLine(0, cellSizeInWidth * i, countOfCellsInLength * cellSizeInLength, cellSizeInWidth * i);
        }
    }
}
