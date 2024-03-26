package view;

import model.entity.BaseEntity;
import model.entity.Robot;
import model.entity.Target;

import java.awt.*;
//вроде норм
public class Renderer implements EntityRenderer<BaseEntity> {
    @Override
    public void render(BaseEntity entity, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        if (entity instanceof Robot) {
            DrawEntity.drawRobot(g2d, entity.x,  entity.y,  ((Robot) entity).getRobotDirection());
        } else if (entity instanceof Target) {
            DrawEntity.drawTarget(g2d, entity.x, entity.y);
        }
    }
}
