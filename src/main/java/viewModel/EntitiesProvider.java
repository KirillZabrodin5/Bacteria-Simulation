package viewModel;

import model.entity.AbstractEntity;

import java.util.List;

public interface EntitiesProvider {
    List<AbstractEntity> getEntitiesList();
}
