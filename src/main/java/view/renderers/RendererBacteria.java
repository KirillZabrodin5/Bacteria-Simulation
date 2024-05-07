package view.renderers;

import model.InfoGameWindow;
import model.entity.Bacteria;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererBacteria implements EntityRenderer<Bacteria> {
    @Override
    public void render(Bacteria entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        InfoGameWindow infoGameWindow = new InfoGameWindow();
        int cellSize = infoGameWindow.getCellSize();
        drawBacteria(g2d, infoGameWindow.translate(entity.x), infoGameWindow.translate(entity.y), cellSize);
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
