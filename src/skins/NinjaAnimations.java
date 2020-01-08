package skins;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NinjaAnimations extends BasicAnimations{
    
    public NinjaAnimations(int width, int height, int runAnimationLength, int idleAnimationLength, int deathAnimationLength){
        super(width, height, runAnimationLength, idleAnimationLength, deathAnimationLength);
    }
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setRunAnimationFrame(int index) throws SlickException{
        return new Image("./graphics/ninja/Run__00" + index + ".png");
    }
    
    /**
     * 
     * @param index
     * @return 
     * @throws org.newdawn.slick.SlickException 
     */
    @Override
    public Image setIdleAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/ninja/Idle__00" + index + ".png");
    }
    
    /**
     * 
     * @param index
     * @throws org.newdawn.slick.SlickException
     * @return 
     */
    @Override
    public Image setDeathAnimationFrame(int index) throws SlickException {
        return new Image("./graphics/ninja/Dead__00" + index + ".png");
    }
   
}
