package viewModel;

import log.Logger;
import view.View;

import javax.swing.*;

public class Window extends JFrame {
    public GameWindow createGameWindow(View view, ViewModel viewModel) {
        GameWindow gameWindow = new GameWindow(view, viewModel);
        gameWindow.setSize(400,  400);
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
}
