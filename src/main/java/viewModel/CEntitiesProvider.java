package viewModel;

import model.World;
import model.entity.AbstractEntity;

import java.util.List;

public class CEntitiesProvider implements IEntitiesProvider {
    private final World world;
    public CEntitiesProvider(World world) {
        this.world = world;
    }

    @Override
    public List<AbstractEntity> getEntitiesList() {
        return world.getEntitiesList();
    }
}
