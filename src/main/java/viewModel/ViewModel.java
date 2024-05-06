package viewModel;

import model.Model;
import view.View;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel extends JComponent {
    public ViewModel(Model model, View view) {
        Timer m_timer = new Timer("events generator", true);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                view.onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                model.updateModel();
            }
        }, 0, 10);
        //размер клетки в пикселях считать

    }
}
