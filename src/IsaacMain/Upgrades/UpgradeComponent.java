package IsaacMain.Upgrades;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

/**
 * This interface is used by the UpgradeDecorator and by the Player.
 * 
 */
public interface UpgradeComponent {
    
    public void init(GameContainer gc) throws SlickException;
    
    public void update(GameContainer gc, int delta) throws SlickException;
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    
    /**
     * All the next method are inserted in this interface to allow the use of 
     * the player methods in the decorator class 
     * 
     */
    public int getNumHearts();
    
    public int getNumVoidHearts();
    
    public void setNumHearts(int numHearts);
    
    public void setNumVoidHearts(int NumVoidHearts);
    
    public void setSpeedUpDecorator(UpgradeDecorator speedUpDecorator);
    
    public void setSpeedUp(float speedUp);
    
    public void setShield(boolean shield);
    
    public void getDamaged(int damage);
    
    public Shape getPlayer();
    
    public void setShieldDecorator(UpgradeDecorator shieldDecorator);
    
}
