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
        return Player.getPlayerInstance();
    }
    public void resetStats() ;
    
    public void setCollisionManager(CollisionManager collision);
    
    public boolean isAppear();
            
    public int getNumVoidHearts();
    
    public void setCommands(Mapping options);

    public void setNumHearts(int numHearts);

    public void setNumVoidHearts(int NumVoidHearts);

    public void setSpeedUp(float speedUp);

    public Shape getPlayer();

    public void execute();

    public void setAnimations(Animations animations);

    public void selectAnimations();

    public Animations getAnimations();
    
    public void getDamaged(int damage);
}
