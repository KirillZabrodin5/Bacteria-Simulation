package view;

import model.Model;
import model.entity.BaseEntity;
import viewModel.OurTimer;

import javax.swing.*;
import java.awt.*;

import java.util.List;
//тут не очень нравится реализация render, но хз как сделать лучше
public class PaintEntity extends JPanel {
    Model model = new Model();
    private final List<BaseEntity> list = model.getEntities();
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
