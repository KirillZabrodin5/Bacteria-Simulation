package viewModel;

import log.Logger;
import view.View;

import javax.swing.*;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    public static final int WIDTH_GAME_WINDOW = 500;
    public static final int LENGTH_GAME_WINDOW = 500;

    public MainApplicationFrame(View view) {
        setContentPane(desktopPane);
        addWindow(createLogWindow());
        addWindow(createGameWindow(view));

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public GameWindow createGameWindow(View view) {
        GameWindow gameWindow = new GameWindow(view);
        gameWindow.setSize(WIDTH_GAME_WINDOW, LENGTH_GAME_WINDOW);
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
        CreateMenu createMenu = new CreateMenu(this);
        menuBar.add(createMenu.createLookAndFeelMenu());
        menuBar.add(createMenu.createTestMenu());
        return menuBar;
    }
}
