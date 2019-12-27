package StaticEnemy;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class AcidLake implements StaticDamage {

    private Shape hitbox;

    public AcidLake(int x, int y) {
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
