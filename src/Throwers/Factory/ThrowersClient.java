package Throwers.Factory;

import Throwers.Thrower;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.tiled.TiledMap;

/*
    This class is the client of the EntityFactory. In fact, the scope of this
    class is the creation of an ArrayList<Thrower> whose content change according
    to the LayerName of the TileMap.
*/
public class ThrowersClient {
    private final TiledMap map;
    private int difficulty;
    private ThrowersFactory et;
    
    public ThrowersClient(TiledMap map, int difficulty) {
        this.map = map;
        this.difficulty = difficulty;
    }
    
    public ArrayList<Thrower> getThrowers(String layerName){
        int x, y, z;
        int layerIndex = this.map.getLayerIndex(layerName);
        int sizeLayer = this.map.getLayerIndex(layerName+"Max");
        ArrayList<Thrower> trl = new ArrayList<>();
       
        
        ArrayList<Integer> cases = new ArrayList<>();
        
        if("Fire".equals(layerName)){
            et = new ConcreteFlameThrowerFactory(this.difficulty);
            cases.add(31);
            cases.add(32);
            cases.add(33);
            cases.add(34);
        }else{
            et = new ConcreteLaserThrowerFactory(this.difficulty);
            cases.add(26);
            cases.add(27);
            cases.add(28);
            cases.add(29);
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
                        while(map.getTileId(x, z, sizeLayer) != 23){
                            z--;
                        }
                        t = et.create(x*30, y*30, y-z, 1);
                        trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(1)) {
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != 21){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-x, 2);
                    trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(2)){
                    z = y;
                    while(map.getTileId(x, z, sizeLayer) != 22){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-y, 3);
                    trl.add(t);
                }else if(map.getTileId(x, y, layerIndex) == cases.get(3)){
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != 24){
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