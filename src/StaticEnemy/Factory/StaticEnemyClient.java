package StaticEnemy.Factory;

import StaticEnemy.StaticDamage;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class StaticEnemyClient {

    /*
     * The "StaticEnemyClient" class uses the concrete StaticEnemy Factories multiple
     * times to create an ArrayList of StaticDamage objects, that will be used
     * by the CollisionManager to check the collisions with those objects.  
     */
    private int x;
    private int y;
    private final int objlayer;
    private final TiledMap map;
    private int difficulty;
    StaticEnemyFactory spikeFactory, acidFactory, barrelFactory;

    /**
     * Constructor to get at the beginning the different factories. Accepts a
     * TiledMap map input and the difficulty (a parameters that changes between
     * 0 and 10.
     *
     * @param map TiledMap map, representing a level.
     * @param difficulty (0-10) value. 
     */
    public StaticEnemyClient(TiledMap map, int difficulty) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("StaticEnemies");
        this.difficulty = difficulty;
        spikeFactory = new SpikeFactory(difficulty);
        acidFactory = new AcidLakeFactory(difficulty);
        barrelFactory = new BarrelFactory(difficulty);
    }

    /**
     * This method is uset to get an array list of objcects, that all share the
     * StaticDamage interface. The different factories will be used to achieve
     * that, depending on the actual ID of the map layer.
     *
     * @return an <pre>{@code ArrayList<StaticDamage>}</pre>.
     */
    public ArrayList<StaticDamage> getStaticEnemyList() {

        ArrayList<StaticDamage> staticDamageArray = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {

                /*
                 * Based on the ID of the tiles in the particular layer of the map
                 * this method creates an array list of different objects that 
                 * match the ID. 
                 */
                if (map.getTileId(x, y, objlayer) == 11 || map.getTileId(x, y, objlayer) == 12) {
                    staticDamageArray.add(spikeFactory.create(x * 30, y * 30, 1));
                } else if (map.getTileId(x, y, objlayer) == 13 || map.getTileId(x, y, objlayer) == 14) {
                    staticDamageArray.add(spikeFactory.create(x * 30, y * 30, 2));
                } else if (map.getTileId(x, y, objlayer) == 61 || map.getTileId(x, y, objlayer) == 62) {
                    staticDamageArray.add(acidFactory.create(x * 30, y * 30, 1));
                } else if (map.getTileId(x, y, objlayer) == 75) {
                    staticDamageArray.add(barrelFactory.create(x * 30, y * 30, 1));
                }
            }
        }

        return staticDamageArray;
    }
}
