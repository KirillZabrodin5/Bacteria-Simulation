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
    private final Timer m_timer = new Timer("events generator", true);
    public ViewModel(Model model, View view) {
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

        RobotAndTarget target = (RobotAndTarget)model.getEntities().get(0);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Mouse, click");
                target.setTargetPosition(e.getPoint());
                System.out.println("Mouse, click end");
            }
        });
    }
}
