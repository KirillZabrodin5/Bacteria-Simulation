package viewModel;

import model.Model;
import model.entity.RobotAndTarget;
import view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        //RobotAndTarget target = (RobotAndTarget)model.getEntities().get(0);
//        addMouseListener(new MouseAdapter()
//        {
//            @Override
//            public void mouseClicked(MouseEvent e)
//            {
//                target.setTargetPosition(e.getPoint());
//            }
//        });
    }
}
