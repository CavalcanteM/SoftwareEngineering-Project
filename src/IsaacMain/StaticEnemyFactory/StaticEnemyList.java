package IsaacMain.StaticEnemyFactory;

import Entities.StaticDamage.*;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class StaticEnemyList {

    /*
        The "StaticDamageList" class uses the concrete factories multiple
        times to create an ArrayList of StaticDamage objects, that will be used
        by the CollisionManager for checking the collisions. 
     */

    private int x;
    private int y;
    private final int objlayer;
    private final TiledMap map;

    public StaticEnemyList(TiledMap map) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("StaticEnemies");
    }

    public ArrayList<StaticDamage> getStaticEnemyList() {
        StaticEnemyFactory oneFactory = new OneHeartSpikeFactory();
        StaticEnemyFactory halfFactory = new HalfHeartSpikeFactory();
        //AcidLakeFactory acidFactory = new AcidLakeFactory(); late implement suggestion

        ArrayList<StaticDamage> StaticDamage = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {

                /*
                Based on the ID of the tiles in the particular layer of the map
                this method creates an array list of different objects that 
                match the ID. 
                 */
                if (map.getTileId(x, y, objlayer) == 1) {
                    StaticDamage.add(oneFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == 2) {
                    StaticDamage.add(halfFactory.create(x * 30, y * 30));
                }
            }
        }
        return StaticDamage;
    }
}