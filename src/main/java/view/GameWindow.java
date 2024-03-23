package view;

import model.Model;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
//конструктор этого класса создает окно игровое поле
public class GameWindow extends JInternalFrame
{
    private final Model m_visualizer;
    public GameWindow()
    {
        
        super("Игровое поле", true, true, true, true);
        m_visualizer = new Model();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
