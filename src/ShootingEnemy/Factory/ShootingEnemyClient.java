package ShootingEnemy.Factory;

import IsaacMain.TileID;
import ShootingEnemy.ShootingEnemy;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.Collections;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class ShootingEnemyClient {

    /**
     * The "ShootingEnemyClient" class uses the concrete Shooting Enemy
     * Factories in different situations to create an ArrayList of StaticDamage
     * objects, that will be used by the CollisionManager to check the
     * collisions with the turrets, in particular.
     */
    private int x, y, difficulty, turret_number;
    private final int turretsLayer, turretsHitboxLayer, hiddenTurretsLayer;
    private final TiledMap map;

    /**
     * Constructor, the corresponding layer index of the different levels of the
     * map are taken, in particualr refering to "Turrets", "TurretsHitbox" and
     * "HiddenTurrets". Finaly, the difficulty parameter will be used a
     * multiplier in some cases to increase the provoked damage.
     *
     * @param map = Tiled map object.
     * @param difficulty the multiplier.
     */
    public ShootingEnemyClient(TiledMap map, int difficulty) {
        this.map = map;
        this.turretsLayer = this.map.getLayerIndex("Turrets");
        this.turretsHitboxLayer = this.map.getLayerIndex("TurretsHitbox");
        this.hiddenTurretsLayer = this.map.getLayerIndex("HiddenTurrets");
        this.difficulty = difficulty;

    }

    public ArrayList<ShootingEnemy> getList() {

        ShootingEnemyFactory threeFactory = new ThreeShootsTurretFactory();
        ShootingEnemyFactory randomthreeshotFactory = new HiddenThreeShotTurretFactory();

        ArrayList<ShootingEnemy> array = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                /**
                 * Based on the ID of the tiles in the particular layer of the
                 * map this method creates an array list of different objects
                 * that match the ID.
                 */
                if (map.getTileId(x, y, turretsLayer) >= TileID.TURRET_UP && map.getTileId(x, y, turretsLayer) <= TileID.TURRET_LEFT) {
                    array.add(threeFactory.create(x, y, calculateHitboxArea(x, y), difficulty));
                }
                try {
                    if (map.getTileId(x, y, hiddenTurretsLayer) >= TileID.TURRET_UP && map.getTileId(x, y, hiddenTurretsLayer) <= TileID.TURRET_LEFT) {
                        array.add(randomthreeshotFactory.create(x, y, calculateHitboxArea(x, y), difficulty));
                    }
                } catch (Exception e) {
                }

            }
        }
        /**
         * This section returns only x turrents on the total of n possible
         * turrets in the map. It's used a shuffle on the arraylist to randomly
         * change the order and pick a subList of only x elements. Then convert
         * it back to an arraylist before returning. NOTE: You have to call
         * shuffle 2 time to make it actually "random".
         */

        this.turret_number = (int) ceil((float) (difficulty * array.size()) / 10);
        Collections.shuffle(array);
        Collections.shuffle(array);
        return new ArrayList<ShootingEnemy>(array.subList(0, this.turret_number));
    }

    /**
     * This method is used to get the actual final shape of the hibox of the
     * turret, in particular merging all the contiguous blocks in the
     * LayerHibbox layer of the map.
     */
    private Shape calculateHitboxArea(int x, int y) {

        int hitboxID = map.getTileId(x, y, turretsHitboxLayer);
        Shape[] temp = {null};
        Shape[] result = {null};
        int j = 0, k = 0;
        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                if (map.getTileId(x, y, turretsHitboxLayer) == hitboxID) {
                    if (k == 0) {
                        temp[0] = new Rectangle(x * 30, y * 30, 30, 30);
                        k++;
                    } else {
                        temp = temp[0].union(new Rectangle(x * 30, y * 30, 30, 30));
                    }
                }
            }
            if (j == 0 && k == 1) {
                result[0] = temp[0];
                j++;
            } else if (j != 0) {
                result = temp[0].union(result[0]);
            }
            k = 0;
        }
        return result[0];
    }
}
