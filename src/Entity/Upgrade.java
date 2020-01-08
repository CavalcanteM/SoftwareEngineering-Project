package Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * This class represent the objects that the player has to collect to join a 
 * powerup.
 * This class is one of the Concrete Product Of the EntityFactory.
 */
public class Upgrade implements Entity {

    private Shape Hitbox;
    private final int x;
    private final int y;

    /**
     * Constructor of the class Upgrade
     * @param x
     * @param y
     */
    public Upgrade(int x, int y) {
        this.x = x;
        this.y = y;
        this.Hitbox = new Rectangle(x, y, 30, 30);
    }

    /**
     * method getHitbox inherited from Entity
     * @return the upgrade hitbox
     */
    @Override
    public Shape getHitBox() {
        return Hitbox;
    }
    
    /**
     * This class has this method implemented because the hitbox of a powerup
     * changes according to the size of the reward image.
     * @param i
     * @param j 
     */
    @Override
    public void setHeightAndWidth(int i, int j) {
        this.Hitbox = new Rectangle(this.x, this.y, j, i);
    }
}