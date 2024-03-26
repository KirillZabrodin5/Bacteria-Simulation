package view;

import model.Model;
import model.entity.BaseEntity;

import javax.swing.*;
import java.awt.*;

import java.util.List;
public class View extends JPanel {
    private final List<BaseEntity> list;

    public View(Model model) {
        list = model.getEntities();
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Renderer renderer = new Renderer();
        for (BaseEntity baseEntity : list) {
            renderer.render(baseEntity, g);
        }
    }
}
