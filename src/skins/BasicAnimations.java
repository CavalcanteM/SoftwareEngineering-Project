package skins;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class BasicAnimations implements Animations{
    
    protected Animation rightAnimation;
    protected Animation leftAnimation;
    protected Animation idleAnimationRight;
    protected Animation idleAnimationLeft;
    protected Animation deathAnimationRight;
    protected Animation deathAnimationLeft;
    public final int RUN_ANIMATION_LENGTH;
    public final int IDLE_ANIMATION_LENGTH;
    public final int DEATH_ANIMATION_LENGTH;
    public int WIDTH;
    public int HEIGHT;
    
    /**
     * Constructor of class Basic Animations
     * @param width the width of the hitbox in pixels
     * @param height the height of the hitbox in pixels
     * @param runAnimationLength number of frames of run animation
     * @param idleAnimationLength number of frames of idle animation
     * @param deathAnimationLength number of frames of death animation
     */
    public BasicAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        this.RUN_ANIMATION_LENGTH = runAnimationLength;
        this.IDLE_ANIMATION_LENGTH = idleAnimationLength;
        this.DEATH_ANIMATION_LENGTH = deathAnimationLength;
        this.WIDTH = width;
        this.HEIGHT = height;
    }
    
    @Override
    public Animation getRightAnimation(){
        return rightAnimation;
    }
    
    @Override
    public Animation getLeftAnimation(){
        return leftAnimation;
    }
    
    @Override
    public Animation getIdleAnimationRight(){
        return idleAnimationRight;
    }
    
    @Override
    public Animation getIdleAnimationLeft(){
        return idleAnimationLeft;
    }
    
    @Override
    public Animation getDeathAnimationRight(){
        return deathAnimationRight;
    }
    
    @Override
    public Animation getDeathAnimationLeft(){
        return deathAnimationLeft;
    }
    
    @Override
    public int getRunAnimationLength(){
        return RUN_ANIMATION_LENGTH;
    }
    
    @Override
    public int getIdleAnimationLength(){
        return IDLE_ANIMATION_LENGTH;
    }
    
    @Override
    public int getDeathAnimationLength(){
        return DEATH_ANIMATION_LENGTH;
    }    
    
    /**
     * Creates the animations for the character
     * @throws SlickException
     */
    @Override
    public void createAnimations() throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Image[] frames = new Image[RUN_ANIMATION_LENGTH];
        rightAnimation = new Animation(); 
        leftAnimation =  new Animation();
        for(int i=0; i<frames.length; i++)
        {
            // Adding current image to animation for moving to the right
            frames[i] = this.setRunAnimationFrame(i).getScaledCopy(WIDTH, HEIGHT);;
            rightAnimation.addFrame(frames[i], 60);
            // Flip and add current image to animation for moving to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            leftAnimation.addFrame(frames[i], 60);
        }
        
        // Create the animations for character not moving (idle animation)
        frames = new Image[IDLE_ANIMATION_LENGTH];
        idleAnimationRight = new Animation();
        idleAnimationLeft =  new Animation();
        for(int i=0; i<frames.length; i++) 
        {
            // Adding current image to animation idle looking to the right
            frames[i] = this.setIdleAnimationFrame(i).getScaledCopy(WIDTH, HEIGHT);
            idleAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation idle looking to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            idleAnimationLeft.addFrame(frames[i], 60);
        }
        
        // Create the animation for the character dying
        frames = new Image[DEATH_ANIMATION_LENGTH];
        deathAnimationRight = new Animation();
        deathAnimationLeft =  new Animation();
        for(int i = 0; i < frames.length; i++)
        {
            // Adding current image to animation death falling to the right
            frames[i] = this.setDeathAnimationFrame(i).getScaledCopy(WIDTH, HEIGHT);
            deathAnimationRight.addFrame(frames[i], 60);
            // Flip and add current image to the animation death falling to the left
            frames[i] = frames[i].getFlippedCopy(true, false);
            deathAnimationLeft.addFrame(frames[i], 60);
        }
    }
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    public abstract Image setRunAnimationFrame(int index) throws SlickException;
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    public abstract Image setDeathAnimationFrame(int index) throws SlickException;
    
    /**
     * 
     * @param index
     * @throws org.newdawn.slick.SlickException
     * @return 
     */
    public abstract Image setIdleAnimationFrame(int index) throws SlickException;
    
    /**
     * Allows to change the dimensions set in the constructor
     * @param newWidth
     * @param newHeight 
     */
    public void setDimensions(int newWidth, int newHeight){
        this.WIDTH = newWidth;
        this.HEIGHT = newHeight;
    }
}
