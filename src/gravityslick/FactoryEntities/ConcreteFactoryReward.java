package gravityslick.FactoryEntities;

import gravityslick.Entity.Reward;
import gravityslick.Entity.Entity;

public class ConcreteFactoryReward extends EntityFactory{
    
    @Override
    public Entity create(int x, int y){
        return new Reward(x, y);
    }
}