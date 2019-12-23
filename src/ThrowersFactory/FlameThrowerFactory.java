package ThrowersFactory;

import Throwers.FlameThrower;
import Throwers.Thrower;

public class FlameThrowerFactory extends ThrowersFactory{

    @Override
    public Thrower create(int x, int y, int size, int type) {
        return new FlameThrower(x,y,size,type);
    }
    
}