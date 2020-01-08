package Entity;

import org.newdawn.slick.geom.Shape;

/**
 * This class is the interface common to all products that the EntityFactory creates
 */
public interface Entity {
  
    /**
     * 
     * @return a Shape object 
     */ 
    public Shape getHitBox();
    
    /**
     * 
     * @param i
     * @param y 
     */
    public void setHeightAndWidth(int i, int y);
    
}