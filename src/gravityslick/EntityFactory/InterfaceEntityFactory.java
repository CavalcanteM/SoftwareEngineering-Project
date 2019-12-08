package gravityslick.Factory;

import Entities.Entity;
import java.util.ArrayList;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/* 
 * Since we are adopting the Factory Method pattern, 
 * we use this abstract class as the Creator class.
*/

public interface  InterfaceEntityFactory{
   

   
    
    // Returns a different collection of Shapes depending on the implementation
    public abstract ArrayList<Entity> getEntities();

    
}