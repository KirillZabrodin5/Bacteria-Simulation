package RunApplication;

import model.Model;
import view.View;
import viewModel.EntitiesProvider;
import viewModel.EntitiesProviderFromModelToView;
import viewModel.MainApplicationFrame;
import viewModel.ViewModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            EntitiesProvider entitiesProvider = new EntitiesProviderFromModelToView(model);
            View view = new View(entitiesProvider);
            ViewModel viewModel = new ViewModel(model, view);

            MainApplicationFrame frame = new MainApplicationFrame(viewModel.getView());
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
