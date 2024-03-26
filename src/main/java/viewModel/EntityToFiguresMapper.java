package viewModel;

import model.Model;
import model.entity.BaseEntity;
import view.EntityRenderer;

import java.util.List;
import java.util.Map;


//public class EntityToFiguresMapper {
//    private final Model model;
//
//    private Map<Class<?>, EntityRenderer<?>> renderes = Map.of(
//            BaseEntity.class, new EntityRenderer()
//    );
//
//    public EntityToFiguresMapper(){
//        model = new Model();
//    }
//
//    public void updateLogic() {
//         model.updateModel();
//    }
//
//    public void render() {
//        List<BaseEntity> entities = model.getEntities();
//        for (BaseEntity entity : entities) {
//            EntityRenderer<BaseEntity> entityRenderer = (EntityRenderer<BaseEntity>) renderes.get(entity.getClass());
//            entityRenderer.render(entity);
//        }
//
//    }

//    private final Model model;
//    private final Batch batch = new SpriteBatch();
//    private Map<Class<?>, EntityRenderer<?>> renderes = Map.of(
//            TextureEntity.class, new TextureRenderer()
//    );
//
//    public ViewModel() {
//        textureContainer = new TextureContainer();
//        model = new Model(textureContainer);
//    }
//
//    public void updateLogic() {
//        model.updateModel();
//    }
//
//    public void render() {
//        List<Entity> entities = model.getEntities();
//        batch.begin();
//        for (Entity entity : entities) {
//            EntityRenderer<Entity> entityRenderer = (EntityRenderer<Entity>) renderes.get(entity.getClass());
//            entityRenderer.render(entity, batch);
//        }
//        batch.end();
//    }
//
//    public void dispose() {
//        textureContainer.dispose();
//        batch.dispose();
//    }


//    public List<Sprite> map() {
//        List<BaseEntity> l = model.getEntities();
//        for (BaseEntity entity : l) {
//            if (entity instanceof Robot) {
//                Robot robot = (Robot) entity;
//                //Sprite sprite = new Sprite(robot, PaintEntity::drawRobot)
//            }
//        }
//    }
    
//}
