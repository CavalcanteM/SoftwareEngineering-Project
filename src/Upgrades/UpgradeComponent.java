package Upgrades;

import IsaacMain.CollisionManager;
import IsaacMain.Player;
import menu.Mapping;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import skins.Animations;

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

    public static UpgradeComponent getPlayerInstance() {
        return null;
    }
    public void resetStats() ;
    
    public void setCollisionManager(CollisionManager collision);
    
    public boolean isAppear();
            
    public int getNumVoidHearts();

    public boolean hasShield();

    public void setCommands(Mapping options);

    public void setNumHearts(int numHearts);

    public void setNumVoidHearts(int NumVoidHearts);

    public void setSpeedUpDecorator(UpgradeDecorator speedUpDecorator);

    public void setSpeedUp(float speedUp);

    public void setShield(boolean shield);

    public Shape getPlayer();

    public void execute();

    public void setShieldDecorator(UpgradeDecorator shieldDecorator);

    public void setAnimations(Animations animations);

    public void selectAnimations();

    public Animations getAnimations();
    
    public void getDamaged(int damage);
}
