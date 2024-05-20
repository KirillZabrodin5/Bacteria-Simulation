package viewModel;

import model.Model;
import view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ViewModel {
    private final Model model;
    private final View view;

    public ViewModel(Model model, View view) {
        this.model = model;
        this.view = view;
        update();
    }

    public void update() {
        Timer m_timer = new Timer("events generator", true);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                model.updateModel();
                view.updateView();
            }
        }, 1000, 500);
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
