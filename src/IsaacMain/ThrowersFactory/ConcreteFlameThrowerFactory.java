package IsaacMain.ThrowersFactory;

import Entities.Throwers.*;

public class ConcreteFlameThrowerFactory extends ThrowersFactory{

    @Override
    public Thrower create(int x, int y, int size, int type) {
        return new FlameThrower(x,y,size,type);
    }
    
}