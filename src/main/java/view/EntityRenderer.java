package view;

import model.entity.BaseEntity;

public interface EntityRenderer<E extends BaseEntity> {
    void render(E entity);
}