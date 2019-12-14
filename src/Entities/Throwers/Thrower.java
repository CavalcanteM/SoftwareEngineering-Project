package Entities.Throwers;

import org.newdawn.slick.geom.Shape;

public interface Thrower {
    
    //Returns the Shape of the thrower
    public Shape getHitBox();
    
    //Returns true when the thrower is on
    public boolean isActive();
    
    //This method has like scope the activation and disabling the thrower
    public void setReset();

    //Returns the Shape with which if the player collides takes damage
    public Shape getDamageBox();
    
    public void render();
    
    public void update(int delta);
    
    public float getYOffset();
}