package view.renderers;

import model.Direction;
import viewModel.windows.GameWindowConfig;
import model.entity.Bacteria;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererBacteria implements EntityRenderer<Bacteria> {
    @Override
    public void render(Bacteria bacteria, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int cellSizeInLength = GameWindowConfig.getCellSizeInLength();
        int cellSizeInWidth = GameWindowConfig.getCellSizeInWidth();
        int x = (int) GameWindowConfig.translate(bacteria.getCoords().x).getX();
        int y = (int) GameWindowConfig.translate(bacteria.getCoords().y).getY();
        int hp = bacteria.getHealthPoints();
        Direction direction = bacteria.getDirection();
        drawBacteria(g2d, x, y, hp, cellSizeInLength, cellSizeInWidth, direction);
    }

    private static void drawBacteria(Graphics2D g, int x, int y, int hp, int cellSizeInLength, int cellSizeInWidth, Direction direction) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        drawCell(g, x, y, cellSizeInLength, cellSizeInWidth);//это сама бактерия рисуется
        drawHp(g, x, y, hp, cellSizeInLength, cellSizeInWidth); //это рисуется значение hp
        drawEyes(g, x, y, direction); //это рисуются глаза
    }

    private static void drawCell(Graphics2D g, int x, int y, int cellSizeInLength, int cellSizeInWidth) {
        g.setColor(Color.BLUE);
        RendererUtil.fillCell(g, x, y, cellSizeInLength, cellSizeInWidth);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSizeInLength, cellSizeInWidth);
    }

    private static void drawHp(Graphics2D g, int x, int y, int hp, int cellSizeInLength, int cellSizeInWidth) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 22));
        g.drawString(String.valueOf(hp), x + cellSizeInLength / 2 - 10, y + cellSizeInWidth / 2 + 10);
    }

    private static void drawEyes(Graphics2D g, int x, int y, Direction direction) {
        switch (direction) {
            case UP_LEFT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y , 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 8, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 8, y , 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 28, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 28, y , 7, 7);
                break;
            case UP:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y , 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 10, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y , 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 30, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y , 7, 7);
                break;
            case UP_RIGHT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y , 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 12, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 12, y , 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 32, y, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 32, y , 7, 7);
                break;

            case RIGHT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 35, y + 10, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 35, y + 10, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 37, y + 12, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 37, y + 12, 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 35, y + 30, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 35, y + 30, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 37, y + 32, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 37, y + 32, 7, 7);
                break;

            case DOWN_RIGHT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 12, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 12, y + 42, 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 32, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 32, y + 42, 7, 7);
                break;
            case DOWN:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 10, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y + 42, 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 30, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y + 42, 7, 7);
                break;
            case DOWN_LEFT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 10, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 10, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 8, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 8, y + 42, 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 30, y + 40, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 30, y + 40, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 28, y + 42, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 28, y + 42, 7, 7);
                break;
            case LEFT:
                g.setColor(Color.WHITE);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 5, y + 10, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 7, y + 12, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 7, y + 12, 7, 7);

                g.setColor(Color.WHITE);
                g.fillOval(x + 5, y + 30, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(x + 5, y + 30, 10, 10);

                //зрачки
                g.setColor(Color.BLACK);
                g.fillOval(x + 7, y + 32, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x + 7, y + 32, 7, 7);
                break;
        }
    }
}
