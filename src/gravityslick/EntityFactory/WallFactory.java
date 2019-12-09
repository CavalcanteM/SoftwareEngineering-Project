package gravityslick.EntityFactory;

import Entities.Entity;
import Entities.Wall;

public class WallFactory implements InterfaceEntityFactory {

    @Override
    public Entity getEntity(int x, int y) {
        return new Wall(x, y);
    }

}
