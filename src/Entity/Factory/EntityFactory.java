package Entity.Factory;

import Entity.Entity;

/**
 * ******Factory Method - Abstract Facttory Interface ******
 *
 * This class, referring to the Factory Method pattern, is the Abstract Factory
 * class. This class declares the method that returns new product objects that
 * will be implemented by the concrete factories.
 */
public abstract class EntityFactory {

    /**
     * Creation method of an istance of the Entity class.
     *
     * @param x = x coordinate for the creation of the block (top left corner).
     * @param y = y coordinate for the creation of the block (top left corner).
     * @return a new Upgrate instance.
     */
    public abstract Entity create(int x, int y);

}
