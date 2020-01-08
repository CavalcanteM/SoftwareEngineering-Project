package Throwers;

import org.newdawn.slick.geom.Shape;

public interface Thrower {
    
    /** 
     * Returns the Shape of the throwe
     * @return r
     */
    public Shape getHitBox();
    
    /**
     * Returns true when the thrower is o
     * @return n
     */
    public boolean isActive();

    /**
     * Returns the Shape with which if the player collides takes damag
     * @return e
     */
    public Shape getDamageBox();
    
    /**
     * This method has the scope of rendering the ParticleSystem
     */
    public void render();
    
    /**
     * This method has the scope of update each frame the ParticleSyste
     * @param delta
     */
    public void update(int delta);
    
    /**
     * This method has the scope damage the player if its shape collides with
     * the DamageBox Shape
     * @return 
     */
    public int doDamage();
}