package view.renderers;

import viewModel.windows.GameWindowConfig;
import model.entity.Food;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererFood implements EntityRenderer<Food> {
    @Override
    public void render(Food entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSizeInLength = GameWindowConfig.getCellSizeInLength();
        int cellSizeInWidth = GameWindowConfig.getCellSizeInWidth();
        int x = (int) GameWindowConfig.translate(entity.getCoords().x).getX();
        int y = (int) GameWindowConfig.translate(entity.getCoords().y).getY();
        drawFood(g2d, x, y, cellSizeInLength, cellSizeInWidth);
    }
    private static void drawFood(Graphics2D g, int x, int y, int cellSizeInLength, int cellSizeInWidth)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        RendererUtil.fillCell(g, x, y, cellSizeInLength, cellSizeInWidth);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSizeInLength, cellSizeInWidth);
    }

}
