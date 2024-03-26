package view;

import javax.swing.*;

import model.Model;
import viewModel.CreateMenu;
import viewModel.Window;

public class ApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private Model model = new Model();
    private View view = new View(model);
    public ApplicationFrame() {
        setContentPane(desktopPane);
        Window createWindow = new Window();
        addWindow(createWindow.createLogWindow());
        addWindow(createWindow.createGameWindow(view));

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

    public Model getModel() {
        return model;
    }
}
