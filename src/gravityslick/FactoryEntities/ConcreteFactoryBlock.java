package gravityslick.FactoryEntities;

import gravityslick.Entity.Block;
import gravityslick.Entity.Entity;

/*
    This class is one of the Concrete Creators that overrides the base factory
    method of EntityFactory, so it returns a different type of product, in this
    case, a Block
*/
public class ConcreteFactoryBlock extends EntityFactory{
    
    @Override
    public Entity create(int x, int y){
        return new Block(x, y);
    }
}