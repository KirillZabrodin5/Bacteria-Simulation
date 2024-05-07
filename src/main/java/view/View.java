package view;

import model.Model;
import model.entity.AbstractEntity;
import model.entity.Bacteria;
import model.entity.Food;
import model.entity.Poison;
import view.renderers.EntityRenderer;
import view.renderers.RendererPoison;
import view.renderers.RendererBacteria;
import view.renderers.RendererFood;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class View extends JComponent {
    private final List<AbstractEntity> entitiesList;
    private final Map<Class<?>, EntityRenderer<?>> entityRendererMap = Map.of(
            Bacteria.class, new RendererBacteria(),
            Food.class, new RendererFood(),
            Poison.class, new RendererPoison()
    );

    public View(Model model) {
        entitiesList = model.getEntities();//сделать dto между Model view - entitiesProvider интерфейс
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

    public void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }
}
