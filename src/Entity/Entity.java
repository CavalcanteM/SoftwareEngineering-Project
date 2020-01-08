package Entity;

import org.newdawn.slick.geom.Shape;

/**********Factory method - Concrete product interface*************
 * 
 * This class is the interface implemented by all the Concrete Products that the
 * different Concrete Factories of the EntiryFactory interface will create. In
 * particular, the Entity(Product) will be instantiated by the EntityFactory.
 */
public interface Entity {

    /**
     * This method will be implemented by all the Entity package classes. The
     * getHitBox will be used to get the shape that represents the hitbox of the
     * Entity object.
     *
     * @return a Shape object
     */
    public Shape getHitBox();

    /**
     * This method will be used if I need to modify the height and width of the
     * Rectangle Shape that represents the hitbox of the Entity object.
     *
     * @param i
     * @param y
     */
    public void setHeightAndWidth(int i, int y);

}
