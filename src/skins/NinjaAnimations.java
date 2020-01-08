package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NinjaAnimations extends BasicAnimations{
    
    public NinjaAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * Loads the frame for the run animation at the specified index 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/ninja/Run__00" + index + ".png");
    }
    
    /**
     * Loads the frame for the idle animation at the specified index 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/ninja/Idle__00" + index + ".png");
    }
    
    /**
     * Loads the frame for the death animation at the specified index
     * @param index
     * @throws org.newdawn.slick.SlickException
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/ninja/Dead__00" + index + ".png");
    }
   
}
