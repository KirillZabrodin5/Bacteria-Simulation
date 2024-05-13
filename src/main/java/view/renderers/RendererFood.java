package view.renderers;

import utils.GameWindowConfig;
import model.entity.Food;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererFood implements EntityRenderer<Food> {
    @Override
    public void render(Food entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSize = GameWindowConfig.getCellSize();
        drawFood(g2d, entity.x*cellSize, entity.y*cellSize, cellSize);
    }
    private static void drawFood(Graphics2D g, int x, int y, int cellSize)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        RendererUtil.fillCell(g, x, y, cellSize, cellSize);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSize, cellSize);
    }

}
