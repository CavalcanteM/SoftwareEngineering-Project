package gravityslick.Factory;

import Entities.Entity;
import Entities.Spikes.HalfHeartSpike;
import Entities.Spikes.OneHeartSpike;
import gravityslick.Factory.InterfaceFactory;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**  
 * This class has as scope of the adaptation
 * of pixel-by-pixel movements in a TileDMap
 */ 
public class SpikesLayerFactory implements InterfaceFactory{
    
    private int x;
    private int y;
    private final int objlayer;
    private TiledMap map;
    
    /**
     * The constructor needs the TileD map in which
     * the character has to move and extract the ID
     * if the layer called "Obj"
     * @param map
     */
    public SpikesLayerFactory(TiledMap map) {
        
        this.map = map;
        this.objlayer = this.map.getLayerIndex("Spikes");
    }
    
    /**  This method has the scope of the creation of a collection (ArrayList)
     * of shapes in the position of the objects in the "Obj" layer.
     * @return 
     */
    @Override
    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> Spikes = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                
                if(map.getTileId(x,y,objlayer) == 1){
                    Spikes.add( new OneHeartSpike(x*30,y*30));
                }
                else if(map.getTileId(x,y,objlayer) == 2){
                    Spikes.add( new HalfHeartSpike (x*30,y*30));
                }
            }
        }
        return Spikes;
    }
}