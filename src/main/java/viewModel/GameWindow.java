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
    }

    private static void addViewAndViewModel(View view, ViewModel viewModel, JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        panel.add(viewModel, c);
        panel.add(view, c);
    }
}
