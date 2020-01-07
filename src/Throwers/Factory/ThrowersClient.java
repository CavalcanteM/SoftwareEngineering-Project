package Throwers.Factory;

import IsaacMain.TileID;
import Throwers.Thrower;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

/*
    This class is the client of the EntityFactory. In fact, the scope of this
    class is the creation of an ArrayList<Thrower> whose content change according
    to the LayerName of the TileMap.
*/
public class ThrowersClient {
    private final TiledMap map;
    private int difficulty;
    
    public ThrowersClient(TiledMap map, int difficulty) {
        this.map = map;
        this.difficulty = difficulty;
    }
    
    public ArrayList<Thrower> getThrowers(String layerName){
        int x, y, z;
        int layerIndex = this.map.getLayerIndex(layerName);
        int sizeLayer = this.map.getLayerIndex(layerName+"Max");
        ArrayList<Thrower> trl = new ArrayList<>();
        ThrowersFactory et;
        
        ArrayList<Integer> cases = new ArrayList<>();
        
        if("Fire".equals(layerName)){
            et = new ConcreteFlameThrowerFactory(this.difficulty);
            cases.add(TileID.FireUp);
            cases.add(TileID.FireRight);
            cases.add(TileID.FireDown);
            cases.add(TileID.FireLeft);
        }else{
            et = new ConcreteLaserThrowerFactory(this.difficulty);
            cases.add(TileID.LaserUp);
            cases.add(TileID.LaserRight);
            cases.add(TileID.LaserDown);
            cases.add(TileID.LaserLeft);
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
                        while(map.getTileId(x, z, sizeLayer) != TileID.VioletBox){
                            z--;
                        }
                        t = et.create(x*30, y*30, y-z, 1);
                        trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(1)) {
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != TileID.RedBox){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-x, 2);
                    trl.add(t);
                }else if (map.getTileId(x, y, layerIndex) == cases.get(2)){
                    z = y;
                    while(map.getTileId(x, z, sizeLayer) != TileID.GreenBox){
                        z++;
                    }
                    t = et.create(x*30, y*30, z-y, 3);
                    trl.add(t);
                }else if(map.getTileId(x, y, layerIndex) == cases.get(3)){
                    z = x;
                    while(map.getTileId(z, y, sizeLayer) != TileID.YelloBox){
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