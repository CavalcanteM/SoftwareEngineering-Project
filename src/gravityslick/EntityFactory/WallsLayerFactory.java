/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.Factory;

import Entities.Block;
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
public class WallsLayerFactory implements InterfaceEntityFactory {
    
    
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
    public WallsLayerFactory(TiledMap map) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("Obj");
    }
    
    /**  This method has the scope of the creation of a collection (ArrayList)
     * of shapes in the position of the objects in the "Obj" layer.
     * @return 
     */
    @Override
    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> walls = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) there is an object. 
                    If it is present, we create a shape 30x30 pixels in the position of the object. */
                if(map.getTileId(x, y, objlayer) != 0){
                    walls.add(new Block(x*30, y*30));
                }
            }
        }
        return walls;
    }

    
}
