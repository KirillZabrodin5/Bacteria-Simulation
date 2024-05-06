package view;

import RunApplication.Main;
import model.Model;
import model.entity.AbstractEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class View extends JComponent {
    private final List<AbstractEntity> entityList;

    public View(Model model) {
        entityList = model.getEntities();//сделать dto между Model view - entitiesProvider
        setDoubleBuffered(true);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Renderer renderer = new Renderer();
        for (AbstractEntity baseEntity : entityList) {
            renderer.render(baseEntity, g);
        }
        Grid.drawGrid(g);
    }

    public void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
}
