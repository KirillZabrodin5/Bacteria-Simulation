package view.renderers;

import java.awt.*;

public final class RendererUtil {
    public static void fillCell(Graphics g, int x, int y, int width, int height)
    {
        g.fillRect(x, y, width, height);
    }

    public static void drawCell(Graphics g, int x, int y, int width, int height)
    {
        g.drawRect(x, y, width, height);
    }
}
