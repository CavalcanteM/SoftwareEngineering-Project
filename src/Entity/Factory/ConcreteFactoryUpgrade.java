package Entity.Factory;

import Entity.Entity;
import Entity.Upgrade;

/**
 * This class is one of the Concrete Creators that overrides the base factory
 * method of EntityFactory, so it returns a different type of product, in this
 * case, an Upgrade
 */
public class ConcreteFactoryUpgrade extends EntityFactory{
    
    /**
     * Method create inherited from EntityFactory
     * @param x
     * @param y
     * @return 
     */
    @Override
    public Entity create(int x, int y){
        return new Upgrade(x, y);
    }
}