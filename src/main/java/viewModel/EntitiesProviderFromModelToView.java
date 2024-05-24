package viewModel;

import model.World;
import model.entity.AbstractEntity;

import java.util.List;

public class EntitiesProviderFromModelToView implements EntitiesProvider {
    private final World world;
    public EntitiesProviderFromModelToView(World world) {
        this.world = world;
    }

    @Override
    public List<AbstractEntity> getEntitiesList() {
        return world.getEntitiesList();
    }
}
