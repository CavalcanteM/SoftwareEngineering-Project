package Throwers.Factory;

import Throwers.Thrower;
import Throwers.LaserThrower;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 * This class is one of the Concrete Creators that overrides the base factory
 * method of ThrowersFactory, so it returns a different type of product, in this
 * case, a LaserThrower
 */
public class ConcreteLaserThrowerFactory extends ThrowersFactory{

    protected int difficulty;

    public ConcreteLaserThrowerFactory(int difficulty){
        this.difficulty = difficulty;
    }
    /**
     * Method create inherited from the class ThrowersFactory
     * @param x
     * @param y
     * @param size
     * @param type
     * @return 
     */
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
