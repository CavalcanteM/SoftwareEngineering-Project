package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Barrel implements StaticDamage {

    private Shape hitbox;
    private int difficulty;
    private int damage;

    public Barrel(int x, int y, int difficulty, int damage) {
        hitbox = new Rectangle(x, y-10, 30, 40);
        /**
         * The hitbox of the barrel has a 10px offset upwards, since the actual
         * picture of the barrel takes 1 tile and 2/3 of the upper tile.
         *
         * +-------------+
         * |             |
         * |             |
         * |             |
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * +-------------+
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * |XXXXXXXXXXXXX|
         * +-------------+
         *
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
