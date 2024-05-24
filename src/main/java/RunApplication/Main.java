package RunApplication;

import model.World;
import view.View;
import viewModel.EntitiesProvider;
import viewModel.EntitiesProviderFromModelToView;
import viewModel.MainApplicationFrame;
import viewModel.ViewWorld;

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
            World world = new World();
            EntitiesProvider entitiesProvider = new EntitiesProviderFromModelToView(world);
            View view = new View(entitiesProvider);
            ViewWorld viewModel = new ViewWorld(world, view);

            MainApplicationFrame frame = new MainApplicationFrame(viewModel.getView());
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
