import model.Model;
import view.ApplicationFrame;
import view.View;
import viewModel.ViewModel;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main
{
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

          ApplicationFrame frame = new ApplicationFrame(view, viewModel);
          frame.pack();
          frame.setVisible(true);
          frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }
}
