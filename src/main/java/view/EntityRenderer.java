package view;

import model.entity.AbstractEntity;
import java.awt.*;
public interface EntityRenderer<E extends AbstractEntity> {
    void render(E entity, Graphics g);
}