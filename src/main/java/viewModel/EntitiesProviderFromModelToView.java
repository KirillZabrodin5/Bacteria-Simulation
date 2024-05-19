package viewModel;

import model.Model;
import model.entity.AbstractEntity;

import java.util.List;

public class EntitiesProviderFromModelToView implements EntitiesProvider {
    private final Model model;
    public EntitiesProviderFromModelToView(Model model) {
        this.model = model;
    }

    @Override
    public List<AbstractEntity> getEntitiesList() {
        return model.getEntitiesList();
    }
}
