package RunApplication;

import model.Model;
import view.MainApplicationFrame;
import view.View;
import viewModel.ViewModel;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main
{
    private static int widthGameWindow = 1520;
    private static int heightGameWindow = 754;
    public static void main(String[] args) {
      try {
          UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } catch (Exception e) {
          e.printStackTrace();
      }

      SwingUtilities.invokeLater(() -> {
          Model model = new Model();
          View view = new View(model);
          ViewModel viewModel = new ViewModel(model, view);

          MainApplicationFrame frame = new MainApplicationFrame(view, viewModel);
          frame.pack();
          frame.setVisible(true);
          frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }

    public static int getWidthGameWindow() {
        return widthGameWindow;
    }

    public static int getHeightGameWindow() {
        return heightGameWindow;
    }
}
