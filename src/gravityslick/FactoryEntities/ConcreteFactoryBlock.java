package gravityslick.FactoryEntities;


import gravityslick.Entity.Block;
import gravityslick.Entity.Entity;

public class ConcreteFactoryBlock extends EntityFactory{
    
    @Override
    public Entity create(int x, int y){
        return new Block(x, y);
    }
}