package StaticEnemy;

import org.newdawn.slick.geom.Shape;

public class OneHeartSpike extends Spike {

    /*
    A particular implementation of the Spike abstract class. 
     */

    public OneHeartSpike(int x, int y, int difficulty) {
        super(x, y, difficulty);
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
