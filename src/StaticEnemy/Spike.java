package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Spike implements StaticDamage {

    public Shape hitbox;
    protected int difficulty;
    public int damage;

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

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public int doDamage() {
        if (difficulty <5)
            return damage;
        else 
            return damage*2;
    }

}
