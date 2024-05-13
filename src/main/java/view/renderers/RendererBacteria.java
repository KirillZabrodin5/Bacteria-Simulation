package view.renderers;

import utils.GameWindowConfig;
import model.entity.Bacteria;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererBacteria implements EntityRenderer<Bacteria> {
    @Override
    public void render(Bacteria entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSize = GameWindowConfig.getCellSize();
        int x = GameWindowConfig.translate(entity.x);
        int y = GameWindowConfig.translate(entity.y);
        drawBacteria(g2d, x, y, cellSize);
    }
    private static void drawBacteria(Graphics2D g, int x, int y, int cellSize)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        RendererUtil.fillCell(g, x, y, cellSize,cellSize);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSize, cellSize);
    }
}
