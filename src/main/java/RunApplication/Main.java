package RunApplication;

import model.World;
import view.View;
import viewModel.IEntitiesProvider;
import viewModel.CEntitiesProvider;
import viewModel.windows.MainApplicationFrame;
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
            IEntitiesProvider entitiesProvider = new CEntitiesProvider(world);
            View view = new View(entitiesProvider);
            ViewWorld viewModel = new ViewWorld(world, view);

            MainApplicationFrame frame = new MainApplicationFrame(viewModel.getView());
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
