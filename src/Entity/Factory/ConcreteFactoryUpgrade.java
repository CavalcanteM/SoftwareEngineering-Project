package Entity.Factory;

import Entity.Entity;
import Entity.Upgrade;

/**
 * *****Factory Method - Concrete Factory *******
 *
 * This class is one of the Concrete Factories that overrides the base factory
 * method of EntityFactory, so it returns a different type of Concrete Product
 * object using the "create" method and specifying in what starting point
 * location (top left corner of the 30x30 block) will be created. In this case,
 * an Upgrade object with the implemented Entity interface type.
 */
public class ConcreteFactoryUpgrade extends EntityFactory {

    /**
     * Method create inherited from the class EntityFactory, to create one of
     * the Entity objects.
     *
     * @param x = x coordinate for the creation of the block (top left corner).
     * @param y = y coordinate for the creation of the block (top left corner).
     * @return a new Upgrate instance. 
     */
    @Override
    public Entity create(int x, int y) {
        return new Upgrade(x, y);
    }
}
