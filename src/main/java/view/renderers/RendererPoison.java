package view.renderers;

import utils.GameWindowConfig;
import model.entity.Poison;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererPoison implements EntityRenderer<Poison> {
    @Override
    public void render(Poison entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSizeInLength = GameWindowConfig.getCellSizeInLength();
        int cellSizeInWidth = GameWindowConfig.getCellSizeInWidth();
        int x = (int) GameWindowConfig.translate(entity.getCoords().x).getX();
        int y = (int) GameWindowConfig.translate(entity.getCoords().y).getY();
        drawPoison(g2d, x, y, cellSizeInLength, cellSizeInWidth);
    }
    private static void drawPoison(Graphics2D g, int x, int y, int cellSizeInLength, int cellSizeInWidth)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.RED);
        RendererUtil.fillCell(g, x, y, cellSizeInLength, cellSizeInWidth);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSizeInLength, cellSizeInWidth);
    }

}
