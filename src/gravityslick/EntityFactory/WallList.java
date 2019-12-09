/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.EntityFactory;

import Entities.Wall;
import Entities.Entity;
import Entities.Spikes.HalfHeartSpike;
import Entities.Spikes.OneHeartSpike;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Adria
 */
public class WallList  {
    
    
    private int x;
    private int y;
    private final int wallLayer;
    private TiledMap map;
    
    /**
     * The constructor needs the TileD map in which
     * the character has to move and extract the ID
     * if the layer called "Obj"
     * @param map
     */
    public WallList(TiledMap map) {
        this.map = map;
        this.wallLayer = this.map.getLayerIndex("Walls");
    }
    
    /**  This method has the scope of the creation of a collection (ArrayList)
     * of shapes in the position of the objects in the "Obj" layer.
     * @return 
     */
    
    public ArrayList<Entity> getEntities(){
        WallFactory factory = new WallFactory(this.map);
        ArrayList<Entity> walls = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) there is an object. 
                    If it is present, we create a shape 30x30 pixels in the position of the object. */
                if(map.getTileId(x, y, wallLayer) != 0){
                    walls.add(factory.getEntity(x*30, y*30));
                }
            }
        }
        return walls;
    }

    
}
