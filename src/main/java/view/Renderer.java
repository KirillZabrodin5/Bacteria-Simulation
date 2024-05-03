package view;

import RunApplication.Main;
import model.entity.AbstractEntity;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer implements EntityRenderer<AbstractEntity> {
    @Override
    public void render(AbstractEntity entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        drawRobot(g2d, entity.xRobot,  entity.yRobot,  entity.robotDirection, entity.sizeRobot);
        drawTarget(g2d, entity.xTarget, entity.yTarget, entity.sizeRobot);
    }

    private static int round(double value)
    {
        return (int)(value + 0.5);
    }

    private static void fillCell(Graphics g, double x, double y, int width, int height)
    {
        g.fillRect(round(x),round(y), width, height);
    }

    private static void drawCell(Graphics g, double x, double y, int width, int height)
    {
        g.drawRect(round(x),round(y), width, height);
    }

    private static void drawRobot(Graphics2D g, double x, double y, double direction, int sizeRobot)
    {
        int x1 = round(x);
        int y1 = round(y);
        AffineTransform t = AffineTransform.getRotateInstance(direction, x1, y1);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillCell(g, x1, y1, sizeRobot,sizeRobot);
        g.setColor(Color.BLACK);
        drawCell(g, x1, y1, sizeRobot, sizeRobot);
    }

    private static void drawTarget(Graphics2D g, double x, double y, int sizeRobot)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillCell(g, x, y, sizeRobot, sizeRobot);
        g.setColor(Color.BLACK);
        drawCell(g, x, y, sizeRobot, sizeRobot);
    }
}
