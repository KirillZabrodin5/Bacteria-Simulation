package view;

import javax.swing.*;

import log.Logger;
import viewModel.CreateMenu;
//этот класс вроде норм написан и находится на своем месте
public class ApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    
    public ApplicationFrame() {

        setContentPane(desktopPane);

        addWindow(createLogWindow());

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400,  400);

        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
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
