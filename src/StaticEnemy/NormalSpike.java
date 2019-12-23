package StaticEnemy;

import org.newdawn.slick.geom.Shape;

public class NormalSpike extends Spike {

    /*
    A particular implementation of the Spike abstract class. 
     */

    public NormalSpike(int x, int y) {
        super(x, y);
    }

    @Override
    public Shape getHitbox() {
        return super.hitbox;
    }

    @Override
    public int doDamage() {
        return 2;
    }

}
