package gravityslick;

import java.util.ArrayList;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/* 
 * Since we are adopting the Factory Method pattern, 
 * we use this abstract class as the Creator class.
*/

public abstract class FactoryMap{
    private TiledMap map;

    public FactoryMap(TiledMap map) {
        this.map = map;
    }
    
    // Returns a different collection of Shapes depending on the implementation
    public abstract ArrayList<Shape> getShapes();

    public TiledMap getMap() {
        return map;
    }
}