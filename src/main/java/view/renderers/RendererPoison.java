package view.renderers;

import model.InfoGameWindow;
import model.entity.Food;
import model.entity.Poison;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererPoison implements EntityRenderer<Poison> {
    @Override
    public void render(Poison entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSize = new InfoGameWindow().getCellSize();
        drawPoison(g2d, entity.x*cellSize, entity.y*cellSize, cellSize);
    }
    private static void drawPoison(Graphics2D g, int x, int y, int cellSize)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.RED);
        RendererUtil.fillCell(g, x, y, cellSize, cellSize);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSize, cellSize);
    }

}
