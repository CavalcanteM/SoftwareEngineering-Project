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
    
    //This method has the scope of rendering the ParticleSystem
    public void render();
    
    //This method has the scope of update each frame the ParticleSystem
    public void update(int delta);
}