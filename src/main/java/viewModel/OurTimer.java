package viewModel;

import model.Model;
import model.entity.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
public class OurTimer extends JPanel {
    Model model = new Model();
    Target target = (Target) model.getEntities().get(1); //костыль
    private final Timer m_timer = new Timer("events generator", true);
    public OurTimer() {
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
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
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                target.setTargetPosition(e.getPoint());

            }
        });

        setDoubleBuffered(true);
    }
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
}
