package model;


/**
 * Этот класс нужен для того, чтобы содержать методы для создания, изменения и удаления экземпляров модели.
 * Например, методы createEntity, updateEntity, deleteEntity
 * могут быть реализованы для управления сущностями в модели.
 * */

public class ModelContext {
    private final Model model;
    public ModelContext(Model model) {
        this.model = model;
    }
}
