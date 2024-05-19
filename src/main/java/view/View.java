package view;

import model.entity.*;
import view.renderers.*;
import viewModel.EntitiesProvider;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class View extends JComponent {
    private final List<AbstractEntity> entitiesList;
    private final Map<Class<?>, EntityRenderer<?>> entityRendererMap = Map.of(
            Bacteria.class, new RendererBacteria(),
            Food.class, new RendererFood(),
            Poison.class, new RendererPoison(),
            Wall.class, new RendererWall()
    );

    public View(EntitiesProvider entitiesProvider) {
        this.entitiesList = entitiesProvider.getEntitiesList();
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (AbstractEntity abstractEntity : entitiesList) {
            EntityRenderer<AbstractEntity> renderer = (EntityRenderer<AbstractEntity>) entityRendererMap
                    .get(abstractEntity.getClass());
            renderer.render(abstractEntity, g);
        }
        Grid.drawGrid(g);
    }

    public void updateView() {
        EventQueue.invokeLater(this::repaint);
    }
}
