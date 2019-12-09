package gravityslick.EntityFactory;

import Entities.Entity;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class RewardList {

    /*
    The "RewardList" class uses the concrete RewardFactory factory multiple
    times to create an array list of Reward objects, that will be used by the
    CollisionManager both for checking the collisions. 
     */
    
    private int x;
    private int y;
    private final int rewardlayer;
    private TiledMap map;

    /**
     * The constructor needs the TileMap object and extract the ID of the 
     * needed layer.
     * @param map
     */
    public RewardList(TiledMap map) {
        this.map = map;
        this.rewardlayer = this.map.getLayerIndex("Reward");
    }

    public ArrayList<Entity> getEntities() {

        RewardFactory factory = new RewardFactory();
        ArrayList<Entity> Reward = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                /*  
                In this "if" we check if in the specific layer of the map with 
                coordinates(x,y) there is an object. If it is present, we create
                a shape 30x30 pixels in the position of the object. 
                */
                if (map.getTileId(x, y, rewardlayer) != 0) {
                    Reward.add(factory.getEntity(x * 30, y * 30));
                }
            }
        }
        return Reward;
    }

}
