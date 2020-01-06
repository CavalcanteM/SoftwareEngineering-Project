package Entity;

import Entity.Factory.ConcreteFactoryReward;
import Entity.Factory.ConcreteFactoryBlock;
import Entity.Factory.EntityFactory;
import Entity.Factory.ConcreteFactoryUpgrade;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/*
    This class is the client of the EntityFactory. In fact, the scope of this
    class is the creation of an ArrayList<Entity> whose content change according
    to the LayerName of the TileMap.
 */
public class EntityClient {

    private static EntityFactory et;
    private final TiledMap map;

    public EntityClient(TiledMap map) {
        this.map = map;
    }

    /*
        Returns an ArrayList<Block> if layerName == "Obj
        Returns an ArrayList<Rewards> if layerName == "Rewads"
    Returns an ArrayList<Upgrades> if layerName == "Upgrade"
     */
    public ArrayList<Entity> getEntities(String layerName) {
        int x, y;

        int layerIndex = this.map.getLayerIndex(layerName);

        ArrayList<Entity> rtl = new ArrayList<>();

        if ("Walls".equals(layerName)) {
            et = new ConcreteFactoryBlock();
        } else if ("Rewards".equals(layerName)) {
            et = new ConcreteFactoryReward();
        } else {
            et = new ConcreteFactoryUpgrade();
        }

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                /**
                 * In this "if" we check if in the tiles with coordinates(x,y)
                 * there is an object. If it is present, we create a shape 30x30
                 * pixels in the position of the object.
                 */
                if (map.getTileId(x, y, layerIndex) != 0) {
                    rtl.add(et.create(x * 30, y * 30));
                }
            }
        }
        return rtl;
    }
}
