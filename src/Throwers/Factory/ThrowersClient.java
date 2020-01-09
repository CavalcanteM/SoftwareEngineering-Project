package Throwers.Factory;

import IsaacMain.TileID;
import Throwers.Thrower;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class is the client of the EntityFactory. In fact, the scope of this
 * class is the creation of an <pre>{@code ArrayList<Thrower>}</pre>; whose 
 * content change according to the LayerName of the TileMap.
 */
public class ThrowersClient {
    private final TiledMap map;
    private int difficulty;
    private ThrowersFactory et;
    
    /**
     * Constructor of the class ThrowersClient
     * @param map the TiledMap representing the current level
     */
    public ThrowersClient(TiledMap map, int difficulty) {
        this.map = map;
        this.difficulty = difficulty;
    }
    
    /**
     * @return an <pre>{@code ArrayList<FlameThrower>}</pre> if layerName == "Fire",
     * an <pre>{@code ArrayList<LaserThrower>}</pre> if layerName == "Laser",
     */
    public ArrayList<Thrower> getThrowers(String layerName){
        int x, y, z;
        int layerIndex = this.map.getLayerIndex(layerName);
        int sizeLayer = this.map.getLayerIndex(layerName+"Max");
        ArrayList<Thrower> trl = new ArrayList<>();
       
        
        ArrayList<Integer> cases = new ArrayList<>();
        
        if("Fire".equals(layerName)){
            et = new ConcreteFlameThrowerFactory(this.difficulty);
            cases.add(TileID.FIRE_UP);
            cases.add(TileID.FIRE_RIGHT);
            cases.add(TileID.FIRE_DOWN);
            cases.add(TileID.FIRE_LEFT);
        }else{
            et = new ConcreteLaserThrowerFactory(this.difficulty);
            cases.add(TileID.LASER_UP);
            cases.add(TileID.LASER_RIGHT);
            cases.add(TileID.LASER_DOWN);
            cases.add(TileID.LASER_LEFT);
        }
        
        Thrower t;        
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) of the
                    layerName layer, there is an object with the specified ID.
                    If it is present, we create a Thrower and then set its DamageBox.
                */
                if(map.getTileId(x, y, layerIndex) == cases.get(0)) {
                     z = y;
                        while(map.getTileId(x, z, sizeLayer) != TileID.VIOLET_BOX){
                            z--;
                        }
                        t = et.create(x*30, y*30, y-z, 1);
                        trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(1)) {
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != TileID.RED_BOX){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-x, 2);
                    trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(2)){
                    z = y;
                    while(map.getTileId(x, z, sizeLayer) != TileID.GREEN_BOX){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-y, 3);
                    trl.add(t);
                }else if(map.getTileId(x, y, layerIndex) == cases.get(3)){
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != TileID.YELLOW_BOX){
                        z--;
                    }
                    t = et.create(x*30, y*30, x-z, 4);
                    trl.add(t);
                }
            }
        }
        return trl;
    }
}