package StaticEnemy.Factory;

import IsaacMain.TileID;
import StaticEnemy.StaticDamage;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class StaticEnemyClient {

    /*
     * The "StaticDamageList" class uses the concrete factories multiple
     * times to create an ArrayList of StaticDamage objects, that will be used
     * by the CollisionManager for checking the collisions. 
     */
    private int x;
    private int y;
    private final int objlayer;
    private final TiledMap map;
    private int difficulty;

    public StaticEnemyClient(TiledMap map, int difficulty) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("StaticEnemies");
        this.difficulty = difficulty;
    }

    public ArrayList<StaticDamage> getStaticEnemyList() {
        StaticEnemyFactory oneFactory = new OneHeartSpikeFactory(difficulty);
        StaticEnemyFactory halfFactory = new HalfHeartSpikeFactory(difficulty);
        StaticEnemyFactory acidFactory = new AcidLakeFactory(difficulty);
        StaticEnemyFactory barrelFactory = new BarrelFactory(difficulty);

        ArrayList<StaticDamage> staticDamageArray = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {

                /*
                 * Based on the ID of the tiles in the particular layer of the map
                 * this method creates an array list of different objects that 
                 * match the ID. 
                 */
                if (map.getTileId(x, y, objlayer) == TileID.NormalSpikeDown || map.getTileId(x, y, objlayer) == TileID.NormalSpikeUp) {
                    staticDamageArray.add(oneFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == TileID.VenSpikeUp || map.getTileId(x, y, objlayer) == TileID.VenSpikeDown) {
                    staticDamageArray.add(halfFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == TileID.AcidlakeTop || map.getTileId(x, y, objlayer) == TileID.BarrelBottom) {
                    staticDamageArray.add(acidFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == TileID.BarrelBottom ) {
                    staticDamageArray.add(barrelFactory.create(x * 30, y * 30));
                }
            }
        }

        return staticDamageArray;
    }
}
