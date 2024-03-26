package view;

import model.entity.BaseEntity;
import java.awt.*;
//вроде норм
public interface EntityRenderer<E extends BaseEntity> {
    void render(E entity, Graphics g);
}