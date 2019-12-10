package Entities.Entity;

import org.newdawn.slick.geom.Shape;

//This class is the interface common to all products that the EntityFactory creates
public interface  Entity {
  
    //returns a shape  
    public Shape getHitBox();
    
    public void setHeightAndWidth(int i, int y);
    
}