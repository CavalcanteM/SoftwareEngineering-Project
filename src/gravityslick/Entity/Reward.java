package gravityslick.Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/*
    This class represent the objects that the player has to collect to complete
    the current level.
    This class is one of the Concrete Product Of the EntityFactory.
*/
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
    
    /*
        This class has these methods implemented because the hitbox of a reward
        changes according to the size of the reward image.
    */
    @Override
    public void setHeight(int i) {
        this.Hitbox = new Rectangle(this.x, this.y, this.Hitbox.getWidth(), i);
    }

    @Override
    public void setWidth(int i) {
        this.Hitbox = new Rectangle(this.x, this.y, i, this.Hitbox.getWidth());
    }
}