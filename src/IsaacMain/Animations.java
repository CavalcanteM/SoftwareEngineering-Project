/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public interface Animations {
    
    public static final int WIDTH = 58, HEIGHT = 70;
    
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
