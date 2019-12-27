package Throwers.Factory;

import Throwers.Thrower;

public abstract class ThrowersFactory {
    
    public abstract Thrower create(int x, int y, int size, int type);
    
}