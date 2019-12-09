package gravityslick.Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Reward implements Entity {

    private Shape Hitbox;
    private int x, y;

    public Reward(int x, int y) {
        this.x = x;
        this.y = y;
        this.Hitbox = new Rectangle(x, y, 30, 30);
    }

    @Override
    public Shape getHitBox() {
        return Hitbox;
    }
}