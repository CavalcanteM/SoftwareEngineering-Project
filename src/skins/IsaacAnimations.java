/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skins;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public class IsaacAnimations extends BasicAnimations{
    
    public IsaacAnimations(int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    @Override
    public void createAnimations() throws SlickException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Image[] frames = new Image[8];
        super.rightAnimation = new Animation(); 
        super.leftAnimation =  new Animation();
        for(int i=0; i<frames.length; i++)
        {
            // Adding current image to animation for moving to the right
            frames[i] = new Image("./graphics/png/Run (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);  
            super.rightAnimation.addFrame(frames[i], 60);
            // Flip and add current image to animation for moving to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            super.leftAnimation.addFrame(frames[i], 60);
        }
        
        // Create the animations for character not moving (idle animation)
        frames = new Image[10];
        super.idleAnimationRight = new Animation();
        super.idleAnimationLeft =  new Animation();
        for(int i=0; i<frames.length; i++) 
        {
            // Adding current image to animation idle looking to the right
            frames[i] = new Image("./graphics/png/Idle (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            super.idleAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation idle looking to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            super.idleAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Create the animation for the character dying
        frames = new Image[10];
        super.deathAnimationRight = new Animation();
        super.deathAnimationLeft =  new Animation();
        for(int i = 0; i < frames.length; i++)
        {
            // Adding current image to animation death falling to the right
            frames[i] = new Image("./graphics/png/Dead (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            super.deathAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation death falling to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            super.deathAnimationLeft.addFrame(frames[i], 60);
        }
    }
}
