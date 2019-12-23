package Entities.StaticDamage;

import org.newdawn.slick.geom.Shape;

/**
 * The "StaticDamage" interface is used as an interface for every static object
 * that has a fixed positon and can deal damage to the player. 
 */
public interface StaticDamage {
    
    /**
     * 
     * @return 
     */
    public Shape getHitbox();
    
    /**
     * 
     * @return the damage of the obstacle (in half hearts)
     */
    public int doDamage();

}
