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
     * @param x indicates the x position of the block that will be created
     * @param y indicates the y position of the block that will be created
     * @return 
     */
    @Override
    public Entity create(int x, int y){
        return new Block(x, y);
    }
}