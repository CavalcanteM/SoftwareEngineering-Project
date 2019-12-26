package ThrowersFactory;

import Throwers.LaserThrower;
import Throwers.Thrower;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class ConcreteLaserThrowerFactory extends ThrowersFactory{
    
    protected int difficulty;
    
    public ConcreteLaserThrowerFactory(int difficulty){
        this.difficulty = difficulty;
    }
    
    @Override
    public Thrower create(int x, int y, int size, int type) {
        try {
            return new LaserThrower(x,y,size,type, this.difficulty);
        } catch (SlickException ex) {
            Logger.getLogger(ConcreteLaserThrowerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}