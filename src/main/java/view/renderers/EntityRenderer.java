package view.renderers;

import model.entity.AbstractEntity;
import java.awt.Graphics;
public interface EntityRenderer<E extends AbstractEntity> {
    void render(E entity, Graphics g);
}