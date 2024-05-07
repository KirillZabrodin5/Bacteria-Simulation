package viewModel;

import view.View;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame
{
    public GameWindow(View view)
    {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);
        panel.setBackground(Color.BLACK);
        getContentPane().add(panel);
        pack();
        setResizable(false);
    }
}
