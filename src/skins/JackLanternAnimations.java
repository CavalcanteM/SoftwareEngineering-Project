package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class JackLanternAnimations extends BasicAnimations {
    
    public JackLanternAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/jack/Run (" + (index+1) + ").png"); 
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/jack/Idle (" + (index+1) + ").png");
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/jack/Dead (" + (index+1) + ").png");
    }
        
}
