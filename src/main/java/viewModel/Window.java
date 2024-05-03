package viewModel;

import log.Logger;
import view.View;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int widthGameWindow;
    private int heightGameWindow;
    public Window(int widthGameWindow, int heightGameWindow) {
        this.widthGameWindow = widthGameWindow;
        this.heightGameWindow = heightGameWindow;
    }
    public GameWindow createGameWindow(View view, ViewModel viewModel) {
        GameWindow gameWindow = new GameWindow(view, viewModel);
        gameWindow.setSize(widthGameWindow,  heightGameWindow); //1520*770 - растянул окно на весь экран, просто потому что мне как пользователю
                                                    //приятно видеть не маленькое окно, а окно на весь экран
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
