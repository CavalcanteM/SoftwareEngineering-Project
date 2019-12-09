package gravityslick.FactoryEntities;

import gravityslick.Entity.Entity;

public abstract class EntityFactory {
    
    public abstract Entity create(int x, int y);
    
}