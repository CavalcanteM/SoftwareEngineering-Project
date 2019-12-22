package IsaacMain.Upgrades;

import IsaacMain.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * This class is extended by all the type of decorator
 * 
 */
public class UpgradeDecorator implements UpgradeComponent{

    protected UpgradeComponent player;

    public UpgradeDecorator() {
        player = Player.getPlayerInstance();
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public int getNumHearts() {
        return 0;
    }

    @Override
    public void setNumHearts(int numHearts) {
    }

    @Override
    public void setNumVoidHearts(int NumVoidHearts) {
    }

    @Override
    public void setSpeedUp(float speedUp) {
    }

    @Override
    public void setShield(boolean shield) {
    }
    
}
