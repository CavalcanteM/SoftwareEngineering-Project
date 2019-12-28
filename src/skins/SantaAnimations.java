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
public class SantaAnimations extends BasicAnimations{
    
    public SantaAnimations(int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    @Override
    public void createAnimations() throws SlickException{
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Image[] frames = new Image[11];
        this.rightAnimation = new Animation(); 
        this.leftAnimation =  new Animation();
        for(int i=0; i<frames.length; i++)
        {
            // Adding current image to animation for moving to the right
            frames[i] = new Image("./graphics/santa/Run (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);  
            this.rightAnimation.addFrame(frames[i], 60);
            // Flip and add current image to animation for moving to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.leftAnimation.addFrame(frames[i], 60);
        }
        
        // Create the animations for character not moving (idle animation)
        frames = new Image[16];
        this.idleAnimationRight = new Animation();
        this.idleAnimationLeft =  new Animation();
        for(int i=0; i<frames.length; i++) 
        {
            // Adding current image to animation idle looking to the right
            frames[i] = new Image("./graphics/santa/Idle (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            this.idleAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation idle looking to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.idleAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Create the animation for the character dying
        frames = new Image[17];
        this.deathAnimationRight = new Animation();
        this.deathAnimationLeft =  new Animation();
        for(int i = 0; i < frames.length; i++)
        {
            // Adding current image to animation death falling to the right
            frames[i] = new Image("./graphics/santa/Dead (" + (i+1) + ").png").getScaledCopy(WIDTH, HEIGHT);
            this.deathAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation death falling to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            this.deathAnimationLeft.addFrame(frames[i], 60);
        }
    }
}
