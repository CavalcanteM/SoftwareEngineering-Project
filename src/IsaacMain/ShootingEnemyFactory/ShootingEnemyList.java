package IsaacMain.ShootingEnemyFactory;

import Entities.StaticDamage.StaticDamage;
import Entities.Turret.ShootingEnemy;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class ShootingEnemyList {

    /*
        The "StaticDamageList" class uses the concrete factories multiple
        times to create an ArrayList of StaticDamage objects, that will be used
        by the CollisionManager for checking the collisions. 
     */
    private int x;
    private int y;
    private final int turretsLayer, turretsHitboxLayer;
    private final TiledMap map;

    public ShootingEnemyList(TiledMap map) {
        this.map = map;
        this.turretsLayer = this.map.getLayerIndex("Turrets");
        this.turretsHitboxLayer = this.map.getLayerIndex("TurretsHitbox");
    }

    public ArrayList<ShootingEnemy> getList() {

        ShootingEnemyFactory threeFactory = new ThreeShootsTurretFactory();

        ArrayList<ShootingEnemy> array = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {

                /*
                Based on the ID of the tiles in the particular layer of the map
                this method creates an array list of different objects that 
                match the ID. 
                 */
                if (map.getTileId(x, y, turretsLayer) > 14 && map.getTileId(x, y, turretsLayer) < 19) {
                    threeFactory.create( x, y, calculateHitboxArea(x, y) );
                }
            }
        }
        return array;
    }

    private Shape calculateHitboxArea(int x, int y) {

        Shape hitboxArea = null;
        int hitboxID = map.getTileId(x, y, turretsHitboxLayer);

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                if (map.getTileId(x, y, turretsHitboxLayer) == hitboxID) {
                    hitboxArea.union(new Rectangle(x, y, 30, 30));
                }
            }
        }
        return hitboxArea; 
    }
}
