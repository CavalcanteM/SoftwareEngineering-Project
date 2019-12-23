package Entities.StaticDamage;

import org.newdawn.slick.geom.Shape;

public class HalfHeartSpike extends Spike {
    /*
    A particular implementation of the Spike abstract class. 
    */

    public HalfHeartSpike(int x, int y, int difficulty) {
        super(x, y, difficulty);
    }

//    @Override
//    public Shape getHitbox() {
//        return super.hitbox;
//    }

    @Override
    public int doDamage() {
        return super.difficulty*1;

    }
}
