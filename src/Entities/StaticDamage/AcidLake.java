package Entities.StaticDamage;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class AcidLake implements StaticDamage {

    public AcidLake(int x, int y) {
        hitbox = new Rectangle(x, y, 30, 30);
    }

    private Shape hitbox;

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public int doDamage() {
        return 5;
    }

}
