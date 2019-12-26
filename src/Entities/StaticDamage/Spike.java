package StaticEnemies;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * The "Spike" abstract class is used as a "template" to build the specific
 * Spike classes. It has a Shape type variable to keep track of its position 
 * and dimentions. 
 * @author danya
 */
public abstract class Spike implements StaticDamage {
    
    public Shape hitbox;
    protected int difficulty;
    
    public Spike(int x, int y, int difficulty) {
        hitbox = new Rectangle(x + 5, y + 5, 20, 20);
        /**
         * The hitbox is smaller than the actal tile, to avoid that the player
         * is damaged even if he is close to the spike without touching it.
         * 
         * +-------------+
         * |             |
         * |    XXXXX    |
         * |    XXXXX    |
         * |    XXXXX    |
         * |             |
         * +-------------+
         */
        this.difficulty = difficulty;
    }
    
    /**
     * 
     * @return the Shape associated with the spike 
     */
    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public int doDamage() {
        return this.difficulty*5;
    }

}