package viewModel;

import model.World;
import view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ViewWorld {
    private final World world;
    private final View view;

    public ViewWorld(World world, View view) {
        this.world = world;
        this.view = view;
        update();
    }

    public void update() {
        Timer timer = new Timer("events generator", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                world.updateWorld();
            }
        }, 1000, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                view.updateView();
            }
        }, 0, 10);
    }

    public View getView() {
        return view;
    }
}
