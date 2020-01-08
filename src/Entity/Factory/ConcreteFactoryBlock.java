package Entity.Factory;

import Entity.Entity;
import Entity.Block;

/**
 * This class is one of the Concrete Creators that overrides the base factory
 * method of EntityFactory, so it returns a different type of product, in this
 * case, a Block
 */
public class ConcreteFactoryBlock extends EntityFactory{
    
    /**
     * Method create inherited from the class EntityFactory
     * @param x 
     * @param y
     * @return 
     */
    @Override
    public Entity create(int x, int y){
        return new Block(x, y);
    }
}