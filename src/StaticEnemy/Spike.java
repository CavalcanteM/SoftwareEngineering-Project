package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Spike implements StaticEnemy {
    /*
    The "Spike" abstract class is used as a <template> to build the specific
    Spike classes. It has a Shape type variable to keep track of its position 
    and dimentions. 
    */

    public Shape hitbox;

    public Spike(int x, int y) {
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
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public int doDamage() {
        return 5;
    }

}