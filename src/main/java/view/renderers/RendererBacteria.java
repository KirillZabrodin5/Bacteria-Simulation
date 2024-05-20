package view.renderers;

import utils.GameWindowConfig;
import model.entity.Bacteria;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RendererBacteria implements EntityRenderer<Bacteria> {
    @Override
    public void render(Bacteria entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int cellSizeInLength = GameWindowConfig.getCellSizeInLength();
        int cellSizeInWidth = GameWindowConfig.getCellSizeInWidth();
        int x = (int) GameWindowConfig.translate(entity.getCoords().x).getX();
        int y = (int) GameWindowConfig.translate(entity.getCoords().y).getY();
        int hp = entity.getHp();
        drawBacteria(g2d, x, y, hp, cellSizeInLength, cellSizeInWidth);
    }
    private static void drawBacteria(Graphics2D g, int x, int y, int hp, int cellSizeInLength, int cellSizeInWidth)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.BLUE);
        RendererUtil.fillCell(g, x, y, cellSizeInLength, cellSizeInWidth);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 22));
        g.drawString(String.valueOf(hp), x + cellSizeInLength/2 - 10, y + cellSizeInWidth/2 + 10);
        g.setColor(Color.BLACK);
        RendererUtil.drawCell(g, x, y, cellSizeInLength, cellSizeInWidth);
    }
}
