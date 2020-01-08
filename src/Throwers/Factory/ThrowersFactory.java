package Throwers.Factory;

import Throwers.Thrower;
/**
 * This class, referring to the Factory Method pattern, is the Creator class.
 * This class declares the method that returns new product objects.
 */
public abstract class ThrowersFactory {
    /**
     * Creation method of an istance of the Thrower class
     * @param x
     * @param y
     * @return 
     */
    
    public abstract Thrower create(int x, int y, int size, int type);
    
}