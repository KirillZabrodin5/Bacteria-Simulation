package view;

import model.entity.BaseEntity;
import java.awt.*;
public interface EntityRenderer<E extends BaseEntity> {
    void render(E entity, Graphics g);
}