package Entity.Factory;

import Entity.Entity;

/**
 * This class, referring to the Factory Method pattern, is the Creator class.
 * This class declares the method that returns new product objects.
 */
public abstract class EntityFactory {
    
    /**
      * Creation method of an istance of the Entity class
     * @param x indicates the x position of the entity that will be created
     * @param y indicates the y position of the entity that will be created
     * @return 
     */
    public abstract Entity create(int x, int y);
    
}