package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class AcidLake implements StaticDamage {

    private Shape hitbox;
    private int difficulty;
    private int damage;
/**
 * 
 * @param x x position of the acid lake
 * @param y y position of the acid lake
 * @param difficulty difficulty increases the basic damage of the lake
 * @param damage basic damage of the acid lake
 */
    public AcidLake(int x, int y, int difficulty, int damage) {
        hitbox = new Rectangle(x, y+10, 30, 20);
        /**
         * The hitbox is shorter than the actal tile, better represent the
         * hitobx area of the lake. It's the same for the full and half-full
         * lake.
         *
         * +-------------+
         * |             |
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * +-------------+
         */
        this.difficulty = difficulty;
        this.damage = damage;
    }
/**
 * 
 * @return returns the hitbox of the acid lake
 */
    @Override
    public Shape getHitbox() {
        return this.hitbox;
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
