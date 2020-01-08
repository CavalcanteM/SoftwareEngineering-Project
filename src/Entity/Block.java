package Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**********Factory method - Concrete product *************
 * 
 * This class represent the objects on witch the player colliding takes no
 * damage, like walls and platforms on which he can walk. This class is one of
 * the Concrete Product of the EntityFactory.
 */
public class Block implements Entity {

    private Shape hitbox;
    private int x, y;

    /**
     * Constructor of the class Block, taking the dimentions of the final hitbox
     * size.
     *
     * @param x = x coordinte on which I want the block to be located at.
     * @param y = y coordinte on which I want the block to be located at.
     */
    public Block(int x, int y) {
        this.hitbox = new Rectangle(x, y, 30, 30);
        this.x = x;
        this.y = y;
    }

    /**
     * Override of the getHitbox method, implementing the Entity interface.
     *
     * @return a Shape.
     */
    @Override
    public Shape getHitBox() {
        return hitbox;
    }

    /**
     * Method setHeightAndWidth inherited from the class Entity, used of I need
     * to change the size of the htibox.
     *
     * @param i = new height of the rectangle hitbox.
     * @param y = new width of the rectangle hitbox.
     */
    @Override
    public void setHeightAndWidth(int i, int y) {
        this.hitbox = new Rectangle(this.x, this.y, y, i);
    }
}
