package Entity;

import Entity.Factory.ConcreteFactoryReward;
import Entity.Factory.ConcreteFactoryBlock;
import Entity.Factory.EntityFactory;
import Entity.Factory.ConcreteFactoryUpgrade;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class is the client that uses the Concrete EntityFactories to get the
 * needed ArrayList of objects that shares the Entity interface. The target of
 * this class is the creation of an ArrayList<Entity> whose parteciapting
 * objects depends on the name of the layer of the map.
 */
public class EntityClient {

    private static EntityFactory factory;
    private final TiledMap map;

    /**
     * Constructor of the class EntityClient, where a map is needed to iter on
     * the different objects present in the map to generate the correct
     * instances.
     *
     * @param map the TiledMap representing the current level.
     */
    public EntityClient(TiledMap map) {
        this.map = map;
    }

    /**
     * Depending on the passed layername parameter, that could be "Walls",
     * "Rewards" or "Upgrades", the corresponding Concrete Facotry is created.
     * Then, the correct layerIndex of the TiledMap map is taken using the
     * provided "getLayerIndex" method (Slick2D library). This index will be
     * used to iter among all the 30x30 pixels blocks of the map and, if the
     * current block of the map matches the corresponding layer object tipe, a
     * new instance of the Entity extended classes in created by the factory in
     * the given position (the top left corner of the 30x30 block). In other
     * words,we check if in the tiles with coordinates(x,y) there is an object.
     * If it is present, we create a shape 30x30 pixels in the position of the
     * object.
     *
     * @return an ArrayList.
     */
    public ArrayList<Entity> getEntities(String layerName) {
        int x, y;

        int layerIndex = this.map.getLayerIndex(layerName);

        ArrayList<Entity> rtl = new ArrayList<>();

        if ("Walls".equals(layerName)) {
            factory = new ConcreteFactoryBlock();
        } else if ("Rewards".equals(layerName)) {
            factory = new ConcreteFactoryReward();
        } else {
            factory = new ConcreteFactoryUpgrade();
        }

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                if (map.getTileId(x, y, layerIndex) != 0) {
                    rtl.add(factory.create(x * 30, y * 30));
                }
            }
        }
        return rtl;
    }
}
