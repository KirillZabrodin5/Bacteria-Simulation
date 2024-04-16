package view;

import model.Model;
import model.entity.BaseEntity;
import model.entity.RobotAndTarget;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
public class View extends JComponent {
    private final List<BaseEntity> entityList;

    public View(Model model) {

        entityList = model.getEntities();
        setDoubleBuffered(true);
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Renderer renderer = new Renderer();
        for (BaseEntity baseEntity : entityList) {
            renderer.render(baseEntity, g);
        }
    }

    public void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
}
