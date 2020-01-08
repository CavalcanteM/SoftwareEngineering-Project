package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class IsaacAnimations extends BasicAnimations{
    
    public IsaacAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * Loads the frame for the run animation at the specified index 
     * @param index
     * @return 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/png/Run (" + (index+1) + ").png");
    }
    
    /**
     * Loads the frame for the idle animation at the specified index 
     * @param index
     * @return 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/png/Idle (" + (index+1) + ").png");
    }
    
    /**
     * Loads the frame for the death animation at the specified index
     * @param index
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/png/Dead (" + (index+1) + ").png");
    }   
}