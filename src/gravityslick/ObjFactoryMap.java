package gravityslick;

import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**  
 * This class has as scope of the adaptation
 * of pixel-by-pixel movements in a TileDMap
 */ 
public class ObjFactoryMap extends FactoryMap{
    
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
    public ObjFactoryMap(TiledMap map, int idLayer) {
        super(map);
        this.map = super.getMap();
        this.objlayer = idLayer;
    }
    
    /**  This method has the scope of the creation of a collection (ArrayList)
     * of shapes in the position of the objects in the "Obj" layer.
     * @return 
     */
    @Override
    public ArrayList<Shape> getShapes(){
        ArrayList<Shape> rtl = new ArrayList<>();
        for(y = 0; y < map.getHeight(); y++){
            for(x = 0; x < map.getWidth(); x++){
                /*  In this "if" we check if in the tiles with coordinates(x,y) there is an object. 
                    If it is present, we create a shape 30x30 pixels in the position of the object. */
                if(map.getTileId(x, y, objlayer) != 0){
                    rtl.add(new Rectangle(x*30, y*30, 30, 30));
                }
            }
        }
        return rtl;
    }
}