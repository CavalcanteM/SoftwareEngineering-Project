package IsaacMain.ThrowersFactory;

import Entities.Throwers.*;

public class ConcreteLaserThrowerFactory extends ThrowersFactory{

    @Override
    public Thrower create(int x, int y, int size, int type) {
        return new LaserThrower(x,y,size,type);
    }
    
}