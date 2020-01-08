package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Spike implements StaticDamage {

    public Shape hitbox;
    protected int difficulty;
    public int damage;
/**
 * 
 * @param x x position of the acid spike
 * @param y y position of the acid spike
 * @param difficulty difficulty increases the basic damage of the spike
 * @param damage basic damage of the acid spike
 */
    public Spike(int x, int y, int difficulty, int damage) {
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
        this.damage = damage;
    }
/**
 * 
 * @return returns the hitbox of the spike
 */
    @Override
    public Shape getHitbox() {
        return hitbox;
    }
/**
 * Returns the damage it does expressed in player's hearts.
 * @return 
 */
    @Override
    public int doDamage() {
        if (difficulty <5)
            return damage;
        else 
            return damage*2;
    }

}
