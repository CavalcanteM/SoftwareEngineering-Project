package skins;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public interface Animations {
    
    public Animation getRightAnimation();

    public Animation getLeftAnimation();

    public Animation getIdleAnimationRight();

    public Animation getIdleAnimationLeft();

    public Animation getDeathAnimationRight();

    public Animation getDeathAnimationLeft();
    
    public int getRunAnimationLength();
    
    public int getIdleAnimationLength();
    
    public int getDeathAnimationLength();
    
    /**
     * Creates the animations for the character
     * @throws SlickException
     */
    public void createAnimations() throws SlickException;
}
