package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Barrel implements StaticEnemy {

    private Shape hitbox;

    public Barrel(int x, int y) {
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
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public int doDamage() {
        return 1;
    }

}