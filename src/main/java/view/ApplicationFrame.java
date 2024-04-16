package view;

import viewModel.CreateMenu;
import viewModel.ViewModel;
import viewModel.Window;
import javax.swing.*;

public class ApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public ApplicationFrame(View view, ViewModel viewModel) {
        setContentPane(desktopPane);
        Window createWindow = new Window();
        addWindow(createWindow.createLogWindow());
        addWindow(createWindow.createGameWindow(view, viewModel));

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        CreateMenu createMenu = new CreateMenu(this);
        menuBar.add(createMenu.createLookAndFeelMenu());
        menuBar.add(createMenu.createTestMenu());
        return menuBar;
    }
}
