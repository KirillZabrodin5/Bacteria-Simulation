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
        Timer timer = new Timer("events generator", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                model.updateModel();
            }
        }, 1000, 500);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.updateView();
            }
        }, 0, 10);
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
