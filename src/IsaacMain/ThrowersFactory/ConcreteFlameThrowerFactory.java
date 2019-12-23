package IsaacMain.ThrowersFactory;

import Entities.Throwers.*;

public class ConcreteFlameThrowerFactory extends ThrowersFactory{
    
    protected int difficulty;
    
    public ConcreteFlameThrowerFactory(int difficulty){
        this.difficulty = difficulty;
    }
    
    @Override
    public Thrower create(int x, int y, int size, int type) {
        return new FlameThrower(x,y,size,type, this.difficulty);
    }
    
}