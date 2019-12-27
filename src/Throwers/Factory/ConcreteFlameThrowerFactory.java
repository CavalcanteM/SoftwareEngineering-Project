package Throwers.Factory;

import Throwers.FlameThrower;
import Throwers.Thrower;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

public class ConcreteFlameThrowerFactory extends ThrowersFactory{

    @Override
    public Thrower create(int x, int y, int size, int type) {
        try {
            return new FlameThrower(x,y,size,type);
        } catch (SlickException ex) {
            Logger.getLogger(ConcreteFlameThrowerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}