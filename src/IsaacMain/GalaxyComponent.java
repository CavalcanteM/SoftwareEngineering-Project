package IsaacMain;

import Entity.Entity;
import ShootingEnemy.ShootingEnemy;
import StaticEnemy.StaticDamage;
import Throwers.Thrower;
import java.io.Serializable;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Interface at the base of the Composite design pattern used to implement a tree
 * of classes that contains the worlds of the game and the levels of each world
 * @author danya
 */
public interface GalaxyComponent extends Serializable {
    
    public void init(GameContainer gc) throws SlickException;
    
    public void update(GameContainer gc, int delta) throws SlickException;
    
    public void render(GameContainer gc, Graphics g) throws SlickException;
    
    public void add(GalaxyComponent galaxyComponent);
    
    public ArrayList<GalaxyComponent> getChildren();
    
    public GalaxyComponent getChild(int index);
    
    public ArrayList<ShootingEnemy> getShootingEnemy();

    public ArrayList<Entity> getRewards();

    public ArrayList<Entity> getBlock();

    public ArrayList<StaticDamage> getSpikes();
    
    public Powerup getPowerup();

    public Points getPts();
    
    public ArrayList<Thrower> getThrowers();
    
}
