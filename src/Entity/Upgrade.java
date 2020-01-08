package Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * *********Factory method - Concrete product *************
 *
 * This class represent the objects that the player has to collect to get a
 * powerup. This class is one of the Concrete Product Of the EntityFactory.
 */
public class Upgrade implements Entity {

    private Shape Hitbox;
    private final int x;
    private final int y;

    /**
     * Constructor of the class Upgrade, taking the dimentions of the final hitbox
     * size.
     *
     * @param x = x coordinte on which I want the block to be located at.
     * @param y = y coordinte on which I want the block to be located at.
     */
    public Upgrade(int x, int y) {
        this.x = x;
        this.y = y;
        this.Hitbox = new Rectangle(x, y, 30, 30);
    }

    /**
     * Override of the getHitbox method, implementing the Entity interface.
     *
     * @return a Shape. 
     */
    @Override
    public Shape getHitBox() {
        return Hitbox;
    }

    /**
     * Method setHeightAndWidth inherited from the class Entity, used of I need
     * to change the size of the htibox.
     *
     * @param i = new height of the rectangle hitbox.
     * @param y = new width of the rectangle hitbox.
     */
    @Override
    public void setHeightAndWidth(int i, int j) {
        this.Hitbox = new Rectangle(this.x, this.y, j, i);
    }
}
