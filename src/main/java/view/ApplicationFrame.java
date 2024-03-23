package view;

import javax.swing.*;

import log.Logger;
import viewModel.CreateMenu;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается. 
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 *
 */
public class ApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane(); // создается окно с многослойной панелью
    
    public ApplicationFrame() {
        // не понял для чего нужны следующие 3 строки, убрав их - ничего не меняется визуально
//        int inset = 50;
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // создаем объект, отвечающий за
//                                                                             размер, цвета, шрифты экрана
//        setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2); //это строка задает размеры какого-то окна

        setContentPane(desktopPane); //сделали desktopPane панелью контента

        addWindow(createLogWindow()); // создали окно логирования и добавили его в desktopPane, как одно из окон многослойной панели

        GameWindow gameWindow = new GameWindow(); //создали окно для игры
        gameWindow.setSize(400,  400); // и задали ему размеры. Max размеры: 1560*770

        addWindow(gameWindow);  // этот метод, как стек работает.
                                // То есть чем раньше создаем окно, тем оно ниже слоем находится

        setJMenuBar(generateMenuBar()); //создание менюшки
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack(); //автоматическое определение оптимальных размеров окна???
        Logger.debug("Протокол работает");
        return logWindow;
    }
    
    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame); // добавляем окно на desktopPane
        frame.setVisible(true); // и делаем его видимым для пользователя, устанавливая true.
                                // Если будет false, то окно будет не видно
    }

    public JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        CreateMenu createMenu = new CreateMenu(this);
        menuBar.add(createMenu.createLookAndFeelMenu());
        menuBar.add(createMenu.createTestMenu());
        return menuBar;
    }
    
//    protected JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
// 
//        //Set up the lone menu.
//        JMenu menu = new JMenu("Document");
//        menu.setMnemonic(KeyEvent.VK_D);
//        menuBar.add(menu);
// 
//        //Set up the first menu item.
//        JMenuItem menuItem = new JMenuItem("New");
//        menuItem.setMnemonic(KeyEvent.VK_N);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("new");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        //Set up the second menu item.
//        menuItem = new JMenuItem("Quit");
//        menuItem.setMnemonic(KeyEvent.VK_Q);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("quit");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        return menuBar;
//    }
}
