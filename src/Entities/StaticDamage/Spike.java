package Entities.StaticDamage;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Spike implements StaticDamage {
    /*
    The "Spike" abstract class is used as a <template> to build the specific
    Spike classes. It has a Shape type variable to keep track of its position 
    and dimentions. 
    */

    public Shape hitbox;

    public Spike(int x, int y) {
        hitbox = new Rectangle(x, y, 30, 30);
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