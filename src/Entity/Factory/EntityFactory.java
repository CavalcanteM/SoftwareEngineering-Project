package Entity.Factory;

import Entity.Entity;

/*
    This class, referring to the Factory Method pattern, is the Creator class.
    This class declares the method that returns new product objects.
*/
public abstract class EntityFactory {
    
    public abstract Entity create(int x, int y);
    
}