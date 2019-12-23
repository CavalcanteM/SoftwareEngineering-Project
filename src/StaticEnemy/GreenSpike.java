package StaticEnemy;

import org.newdawn.slick.geom.Shape;

public class GreenSpike extends Spike {
    /*
    A particular implementation of the Spike abstract class. 
    */

    public GreenSpike(int x, int y) {
        super(x, y);
    }

    @Override
    public Shape getHitbox() {
        return super.hitbox;
    }

    @Override
    public int doDamage() {
        return 1;

    }
}
