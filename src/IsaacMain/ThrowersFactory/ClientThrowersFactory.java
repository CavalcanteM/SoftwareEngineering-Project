package IsaacMain.ThrowersFactory;

import Entities.Throwers.*;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class ClientThrowersFactory {
    private static ThrowersFactory et;
    private final TiledMap map;

    public ClientThrowersFactory(TiledMap map) {
        this.map = map;
    }
    
    public ArrayList<Thrower> getEntities(String layerName){
        int x, y, z;
        int layerIndex = this.map.getLayerIndex(layerName);
        int sizeLayer = this.map.getLayerIndex(layerName+"Max");
        ArrayList<Thrower> trl = new ArrayList<>();
        
        et = new ConcreteFlameThrowerFactory();
        Thrower t;        
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) of the
                    layerName layer, there is an object with the specified ID.
                    If it is present, we create a Thrower and then set its DamageBox.
                */
                switch (map.getTileId(x, y, layerIndex)) {
                    case 16:
                        z = y;
                        while(map.getTileId(x, z, sizeLayer) != 23){
                            z--;
                        }
                        t = et.create(x*30, y*30, y-z, 1);
                        trl.add(t);
                        break;
                    case 17:
                        z = x;
                        while(map.getTileId(z, y, sizeLayer) != 21){
                            z++;
                        }
                        t = et.create(x*30, y*30, z-x, 2);
                        trl.add(t);
                        break;
                    case 18:
                        z = y;
                        while(map.getTileId(x, z, sizeLayer) != 22){
                            z++;
                        }
                        t = et.create(x*30, y*30, z-y, 3);
                        trl.add(t);
                        break;
                    case 19:
                        z = x;
                        while(map.getTileId(z, y, sizeLayer) != 24){
                            z--;
                        }
                        t = et.create(x*30, y*30, x-z, 4);
                        trl.add(t);
                        break;
                    default:
                        break;
                }
            }
        }
        return trl;
    }
}