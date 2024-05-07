package model.entity;

import model.InfoGameWindow;
import utils.ApplicationMath;

public class Bacteria extends AbstractEntity {
    InfoGameWindow infoGameWindow = new InfoGameWindow();
    public Bacteria(int x, int y) {
        super(x, y);
    }

    public void moveRobot(double velocity, double duration) {
        //findFoodOrPoison
    }
    @Override
    public void update() {
//        moveRobot(maxVelocity, 15);
//        double distance = ApplicationMath.distance(target.x, target.y, x, y);
//        if (distance < infoGameWindow.getCellSize()) {
//            target.createNewInstance();
//        }
    }
}
