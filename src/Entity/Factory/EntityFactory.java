package Entity.Factory;

import Entity.Entity;

/**
 * This class, referring to the Factory Method pattern, is the Creator class.
 * This class declares the method that returns new product objects.
 */
public abstract class EntityFactory {
    
    /**
     * Creation method of an istance of the Entity class
     * @param x
     * @param y
     * @return 
     */
    public abstract Entity create(int x, int y);
    
}