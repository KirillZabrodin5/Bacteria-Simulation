package view;

import model.entity.*;
import view.renderers.*;
import viewModel.IEntitiesProvider;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class View extends JComponent {

    private final IEntitiesProvider entitiesProvider;
    private final Map<Class<?>, EntityRenderer<?>> entityRendererMap = Map.of(
            Bacteria.class, new RendererBacteria(),
            Food.class, new RendererFood(),
            Poison.class, new RendererPoison(),
            Wall.class, new RendererWall()
    );

    public View(IEntitiesProvider entitiesProvider) {
        this.entitiesProvider = entitiesProvider;
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        RendererGrid grid = new RendererGrid();
        for (AbstractEntity entity : entitiesProvider.getEntitiesList()) {
            EntityRenderer<AbstractEntity> renderer = (EntityRenderer<AbstractEntity>) entityRendererMap
                    .get(entity.getClass());
            renderer.render(entity, g);
        }
        grid.drawGrid(g);
    }

    public void updateView() {
        EventQueue.invokeLater(this::repaint);
    }
}
