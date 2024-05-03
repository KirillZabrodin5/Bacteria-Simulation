package view;

import java.awt.*;

public final class Grid {
    public static void drawGrid(Graphics g, int sizeRobot, int widthGameWindow, int heightGameWindow) {
        g.setColor(Color.WHITE);
        int columns = widthGameWindow / sizeRobot;
        int rows = heightGameWindow/sizeRobot;

        // Рисуем вертикальные линии
        for (int i = 0; i <= columns + 5; i++) {
            g.drawLine(sizeRobot * i + i, 0, sizeRobot * i + i, heightGameWindow * sizeRobot + 1);
        }

        // Рисуем горизонтальные линии
        for (int i = 0; i <= rows + 5; i++) {
            g.drawLine(0, sizeRobot * i + i, widthGameWindow * sizeRobot + 1, sizeRobot * i + i);
        }
    }
}
