package gravityslick.EntityFactory;

import Entities.Entity;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class WallList {

    /*
    The "WallList" class uses the concrete WallFactory factory multiple
    times to create an array list of Wall objects, that will be used by the
    CollisionManager for checking the collisions. 
     */

    private int x;
    private int y;
    private final int wallLayer;
    private TiledMap map;

    /**
     * The constructor needs the TileMap object and extract the ID of the 
     * needed layer.
     * @param map
     */
    public WallList(TiledMap map) {
        this.map = map;
        this.wallLayer = this.map.getLayerIndex("Walls");
    }

    public ArrayList<Entity> getEntities() {
        
        WallFactory factory = new WallFactory();
        ArrayList<Entity> walls = new ArrayList<>();
        
        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                /*  
                In this "if" we check if in the specific layer of the map with 
                coordinates(x,y) there is an object. If it is present, we create
                a shape 30x30 pixels in the position of the object. 
                */
                if (map.getTileId(x, y, wallLayer) != 0) {
                    walls.add(factory.getEntity(x * 30, y * 30));
                }
            }
        }
        return walls;
    }

}
