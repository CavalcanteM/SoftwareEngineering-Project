package ThrowersFactory;

import Throwers.Thrower;
import Throwers.LaserThrower;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class LaserThrowerFactory extends ThrowersFactory{

    @Override
    public Thrower create(int x, int y, int size, int type) {
        try {
            return new LaserThrower(x,y,size,type);
        } catch (SlickException ex) {
            Logger.getLogger(LaserThrowerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}