package IsaacMain.FactoryEntities;

import Entities.Entity.*;

/*
    This class is one of the Concrete Creators that overrides the base factory
    method of EntityFactory, so it returns a different type of product, in this
    case, an Upgrade
*/
public class ConcreteFactoryUpgrade extends EntityFactory{
    
    @Override
    public Entity create(int x, int y){
        return new Reward(x, y);
    }
}