package viewModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;

import model.Model;
import model.entity.*;

import model.entity.Robot;
import view.Sprite;

public class EntityToFiguresMapper {
    private final Model model; 
    
    public EntityToFiguresMapper(Model model){
        this.model = model;
    }

    public List<Sprite> map() {
        List<BaseEntity> l = model.getEntities();
        for (BaseEntity entity : l) {
            if (entity instanceof Robot) {
                //Robot robot = (Robot) entity;
                Sprite sprite = new Sprite(entity, () -> {
                    int x1 = (int) x;
                    int y1 = (int) y;
                    AffineTransform t = AffineTransform.getRotateInstance(direction, x, y);
                    g.setTransform(t);
                    g.setColor(Color.MAGENTA);
                    fillOval(g, x, y, 30, 10);
                    g.setColor(Color.BLACK);
                    drawOval(g, x1, y1, 30, 10);
                    g.setColor(Color.WHITE);
                    fillOval(g, x1  + 10, y, 5, 5);
                    g.setColor(Color.BLACK);
                    drawOval(g, x1  + 10, y1, 5, 5);
                });
            }
        }
    }
    
}
