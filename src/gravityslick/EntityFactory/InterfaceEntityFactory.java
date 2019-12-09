package gravityslick.EntityFactory;

import Entities.Entity;

public interface InterfaceEntityFactory {

    /* 
    The "InterfaceEntityFactory" is an interface used to define the method that
    will be implemented by the concrete factories of Entities. 
     */
    
    public Entity getEntity(int x, int y);
    // Returns an object of the Entity type.
}
