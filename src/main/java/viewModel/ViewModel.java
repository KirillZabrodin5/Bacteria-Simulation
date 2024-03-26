package viewModel;

import model.Model;
import model.entity.RobotAndTarget;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
//таймер вроде здесь должен быть, но куда его передавать не ясно
//вью модел должен работать с frames
public class ViewModel extends JPanel {
    private final Model model = new Model();
    private final Timer m_timer = new Timer("events generator", true);
    public ViewModel(Model model, View view) { //может в конструктор передать target
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
                ((RobotAndTarget) model.getEntities().get(0)).setTargetPosition(e.getPoint());
            }
        });

        setDoubleBuffered(true);
    }
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    } //это во вью надо выкинуть
}
