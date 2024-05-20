package viewModel;

import log.Logger;
import utils.GameWindowConfig;
import view.View;

import javax.swing.*;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame(View view) {
        setContentPane(desktopPane);
        //addWindow(createLogWindow());
        addWindow(createGameWindow(view));

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public GameWindow createGameWindow(View view) {
        GameWindow gameWindow = new GameWindow(view);
        gameWindow.setSize(GameWindowConfig.getLengthGameWindow(), GameWindowConfig.getWidthGameWindow());
        return gameWindow;
    }

    public LogWindow createLogWindow()
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
        Menu createMenu = new Menu(this);
        menuBar.add(createMenu.createLookAndFeelMenu());
        menuBar.add(createMenu.createTestMenu());
        return menuBar;
    }
}
