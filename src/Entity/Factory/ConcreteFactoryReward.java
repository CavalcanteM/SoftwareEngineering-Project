package Entity.Factory;

import Entity.Entity;
import Entity.Reward;

/*
    This class is one of the Concrete Creators that overrides the base factory
    method of EntityFactory, so it returns a different type of product, in this
    case, a Block
*/
public class ConcreteFactoryReward extends EntityFactory{
    
    @Override
    public Entity create(int x, int y){
        return new Reward(x, y);
    }
}