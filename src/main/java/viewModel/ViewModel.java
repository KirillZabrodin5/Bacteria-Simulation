package viewModel;

import model.Model;
import model.entity.AbstractEntity;
import view.View;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel {
    private final Model model;
    private final View view;

    public ViewModel() {
        model = new Model();
        view = new View(getEntities());
        update();
    }

    public List<AbstractEntity> getEntities() { //вынести в интерфейс entitiesProvider
        return model.getEntities();
    }

    public void update() {
        Timer m_timer = new Timer("events generator", true);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                model.updateModel();
            }
        }, 1000, 500);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.updateView();
            }
        }, 0, 50);
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
